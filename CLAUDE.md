# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

`myfirstfile` is a single-module Android app (package `com.kangmin.myfirstfile`, applicationId same). It is currently a starter project: one `AppCompatActivity` (`MainActivity`) inflating a static XML layout (`activity_main.xml`) that shows a single `ImageView`. There is no view logic yet — new UI is built by adding views/logic to `MainActivity` and `res/layout/`.

## Commands

Use the Gradle wrapper (`./gradlew`) from the repo root.

- Build debug APK: `./gradlew assembleDebug`
- Build everything: `./gradlew build`
- Unit tests (JVM, in `app/src/test/`): `./gradlew test`
- Single unit test: `./gradlew test --tests "com.kangmin.myfirstfile.ExampleUnitTest.addition_isCorrect"`
- Instrumented tests (require a connected device/emulator, in `app/src/androidTest/`): `./gradlew connectedAndroidTest`
- Lint: `./gradlew lint` (report at `app/build/reports/lint-results-debug.html`)
- Install on device/emulator: `./gradlew installDebug`
- Clean: `./gradlew clean`

## Configuration notes

- **Dependencies are managed via a version catalog** at `gradle/libs.versions.toml`. Add libraries there as `libs.<alias>` entries rather than hardcoding coordinates in `app/build.gradle.kts`.
- **Build tooling is unusually new/preview**: AGP `9.2.1`, Kotlin `2.2.10`, `compileSdk 36` (with `minorApiLevel = 1`), `targetSdk 36`, `minSdk 24`. Syntax like `compileSdk { version = release(36) {...} }` and `buildTypes.release { optimization { enable = false } }` is preview-AGP DSL — expect it to differ from mainstream AGP docs.
- Java/Kotlin source and target compatibility is Java 11.
- Kotlin is used for app code, but the Kotlin Android Gradle plugin alias exists in the catalog and is applied implicitly by the current setup — if adding a second module, apply `libs.plugins.kotlin.android` explicitly.

## Not yet initialized

There is no git repository here (`git init` if version control is wanted) and no README. The app is essentially the Android Studio template plus placeholder `react1`–`react5` drawables in `res/drawable/`.
