import java.util.Properties


plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

fun loadLocalProperties(): java.util.Properties {
    val properties = Properties()
    val localPropertiesFile = project.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { properties.load(it) }
    }
    print(properties)
    return properties
}

val localProperties = loadLocalProperties()

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