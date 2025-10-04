import java.util.Properties


// Load .env and push into Gradle properties
val envFile = rootDir.resolve(".env")
if (envFile.exists()) {
    val props = Properties().apply { load(envFile.inputStream()) }
    props.forEach { key, value ->
        gradle.extra.set(key.toString(), value.toString())
    }
}

rootProject.name = "demo"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")





pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven(url = "https://maven.google.com/")
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication { create<BasicAuthentication>("basic") }
            credentials {
                username = "mapbox"
                password = gradle.extra["MAPBOX_DOWNLOADS_TOKEN"] as String
            }
        }
    }
}

include(":composeApp")
include(":server")
include(":shared")