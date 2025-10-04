import java.util.Properties

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}


val localProperties = Properties()
localProperties.load(rootProject.file("local.properties").inputStream())

val geminiApiKey = localProperties.getProperty("geminiApiKey")

group = "gdg.pjatk.pw.demo"
version = "1.0.0"

application {
    mainClass.set("gdg.pjatk.pw.demo.ApplicationKt")

    val geminiApiKey = localProperties.getProperty("geminiApiKey")
    val isDevelopment: Boolean = project.ext.has("development")

    applicationDefaultJvmArgs = mutableListOf("-Dio.ktor.development=$isDevelopment").apply {
        if (geminiApiKey != null) {
            add("-DGEMINI_API_KEY=$geminiApiKey")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)

    implementation("ai.koog:koog-agents:0.5.0")
}
