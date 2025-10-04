import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

val envFile = rootProject.file(".env")
val env = Properties()
if (envFile.exists()) {
    envFile.inputStream().use { env.load(it) }
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(projects.shared)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "gdg.pjatk.pw.demo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "gdg.pjatk.pw.demo"
        minSdk = 24
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // BuildConfig field for Mapbox token
        buildConfigField(
            "String",
            "MAPBOX_ACCESS_TOKEN",
            "\"${env["MAPBOX_ACCESS_TOKEN"] ?: ""}\""
        )

        // Resource string for XML layouts
        resValue(
            "string",
            "mapbox_access_token",
            env["MAPBOX_ACCESS_TOKEN"]?.toString() ?: ""
        )

        // BuildConfig field for Mapbox token
        buildConfigField(
            "String",
            "MAPBOX_DOWNLOADS_TOKEN",
            "\"${env["MAPBOX_DOWNLOADS_TOKEN"] ?: ""}\""
        )

        // Resource string for XML layouts
        resValue(
            "string",
            "mapbox_downloads_token",
            env["MAPBOX_DOWNLOADS_TOKEN"]?.toString() ?: ""
        )
    }

    buildFeatures {
        buildConfig = true // ✅ enable BuildConfig generation
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


dependencies {
    debugImplementation(compose.uiTooling)
    implementation("com.mapbox.maps:android-ndk27:11.15.2")
    implementation("com.mapbox.extension:maps-compose-ndk27:11.15.2")
    implementation("com.mapbox.navigationcore:android-ndk27:3.16.0-beta.1")
}
