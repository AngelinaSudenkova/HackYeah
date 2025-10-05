# HackYeah
# HackYeah

> Kotlin Multiplatform travel app (Android & iOS) with a shared module and a Python backend (Gemini API).

---

## ✨ User journey

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/ba506913-29d6-4694-9f71-42cdf091aa5c" width="260" alt="Onboarding" />
      <br/><sub><b>1. Onboarding</b><br/>Start exploring</sub>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/49be308b-b855-47fa-838a-d60a6e5fe4d5" width="260" alt="Interests Unselected" />
      <br/><sub><b>2a. Interests</b><br/>Pick your vibe</sub>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/e2122e8c-0a24-4288-a913-2bbeb396b1ec" width="260" alt="Interests Selected" />
      <br/><sub><b>2b. Interests</b><br/>Selected tags + bio</sub>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/ba8edd8a-5a37-4925-81d0-69c8e2d4c4ae" width="260" alt="Germany Selected" />
      <br/><sub><b>3a. Country</b><br/>Germany selected</sub>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/f94ba345-3352-40df-b0c7-1be51ed83713" width="260" alt="Poland Selected" />
      <br/><sub><b>3b. Country</b><br/>Poland selected</sub>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/37b2766b-de16-4923-bee1-8e4f4b539e43" width="260" alt="Poland Selected (Planet view)" />
      <br/><sub><b>3c. Country</b><br/>Planet view</sub>
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/9f256395-7fda-4f25-ba49-f552f8cffe6a" width="260" alt="City chooser" />
      <br/><sub><b>4. City</b><br/>Choose a city</sub>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/0b68a9cc-39b1-40a0-ab39-52061990b149" width="260" alt="Warsaw Selected" />
      <br/><sub><b>5a. Map</b><br/>Warsaw, markers</sub>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/8b761659-4c6a-4a7e-99ad-e6d57307273a" width="260" alt="Kraków Selected" />
      <br/><sub><b>5b. Map</b><br/>Kraków, route builder</sub>
    </td>
  </tr>
</table>

---


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



