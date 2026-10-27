# Compose Multiplatform Playground: Android + iOS

![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white) ![Compose](https://img.shields.io/badge/Compose-Multiplatform-4285F4?logo=jetpackcompose&logoColor=white) ![Platforms](https://img.shields.io/badge/Platforms-Android%20%7C%20iOS-555555)

A **Kotlin Multiplatform** app where all UI and logic live in `shared/commonMain` and run unchanged on **Android and iOS**. Each screen explores a common cross-platform need.

## What's inside

| Screen | Library | Cross-platform concern |
|---|---|---|
| Navigation | [Voyager](https://github.com/adrielcafe/voyager) | Shared screen stack with slide transitions |
| Networking | [Ktor client](https://ktor.io) | Fetch and render JSON (`UserPosts`) with one shared HTTP client |
| ViewModel | [moko-mvvm](https://github.com/icerockdev/moko-mvvm) | ViewModels written once in common code |
| Biometrics | [moko-biometry](https://github.com/icerockdev/moko-biometry) | Face ID / Touch ID on iOS and BiometricPrompt on Android from one API |
| Permissions | [moko-permissions](https://github.com/icerockdev/moko-permissions) | Runtime permission flow, including denied and cancelled states |
| Logging | [Kermit](https://github.com/touchlab/Kermit) | One logger for both platforms |

## Project structure

```
shared/
  src/commonMain/   ← all screens, ViewModels and models
  src/androidMain/  ← Android entry point
  src/iosMain/      ← MainViewController for iOS
androidApp/         ← thin Android host
iosApp/             ← thin SwiftUI host (Xcode project)
```

## Run it

- **Android**: open in Android Studio with the Kotlin Multiplatform plugin and run `androidApp`.
- **iOS**: on a Mac with Xcode, run the `iosApp` configuration from Android Studio, or open `iosApp/iosApp.xcodeproj`.

> Started from JetBrains' Compose Multiplatform mobile template, then extended with the screens above.

---

Built by **[Adarsh Thakur](https://adarsh098765.github.io/adarshthakur.github.io/)**, an Android engineer working with Kotlin and Jetpack Compose · [LinkedIn](https://www.linkedin.com/in/adarsh-thakur-b31281171/)

