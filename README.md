# HackYeah

**Travel app** — Kotlin Multiplatform mobile project (Android & iOS) with a shared module and a python backend (calls to Gemini API).

## Project overview

This repository contains a cross-platform travel application implemented using Kotlin Multiplatform. The project includes:

* `composeApp` — Android & IOS app implemented with Jetpack Compose + Integration of MapBox for Android.
* `iosApp` — iOS app (Swift/SwiftUI) for iOS targets.
* `shared` — Kotlin Multiplatform shared module with business logic, models, and networking.
* `server` — Sending the requests for points of interest to Open street maps API.
* Gradle wrapper and top-level configuration files.

## Features (example)

* Browse places and travel recommendations
* Cross-platform business logic in the `shared` module

## Tech stack

* Kotlin Multiplatform (Kotlin/JVM + Kotlin/Native)
* Jetpack Compose (Android + IOS)
* FastAPI server 
* Gradle Kotlin DSL

## Prerequisites

* JDK 17+ (or version specified in `gradle.properties`)
* Android Studio Flamingo (or Arctic Fox+) with Kotlin Multiplatform support
* Xcode 13+ (for building/running iOS app)
* Gradle (wrapper included)
* [Optional] Postman or HTTP client to test server endpoints

## Quick start

1. Clone the repository:

```bash
git clone https://github.com/AngelinaSudenkova/HackYeah.git
cd HackYeah
```

2. Open the project in Android Studio or IntelijIdea with KMP plugin (recommended)

   * File → Open → select the project root
   * Let Gradle sync and download dependencies


3. Run Android app:

   * Select `composeApp` module, pick an emulator or device, and run

4. Run iOS app (if present):

   * Open `iosApp` in Xcode (if bridging generated framework) or run via Gradle tasks that produce an Xcode framework

## Project structure

```
HackYeah/
├─ composeApp/    # Android app (Jetpack Compose)
├─ iosApp/        # iOS app
├─ server/        # Backend
├─ shared/        # Kotlin Multiplatform shared module
├─ build.gradle.kts
├─ gradle/        # Gradle config
└─ README.md
```


## TODOs / Next steps (suggestions)

* Add Markers on the map
* Integrate MapBox for ios application
* Connect backend to mobile application
* Implement chat screens
* Add CI (GitHub Actions) for multiplatform builds
* Add README screenshots and badges
* Provide environment variables / configuration docs (e.g. API keys)
* Document API endpoints in `server` module or add OpenAPI spec


