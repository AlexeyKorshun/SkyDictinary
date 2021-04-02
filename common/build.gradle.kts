plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.4.31"
    id("com.android.library")
}

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }

    buildToolsVersion("30.0.3")
    compileSdkVersion(30)

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.0.1"
    }
}

val iosExportSuffix = getIosTargetName().toLowerCase()

fun getIosTargetName(): String {
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    return "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
}

kotlin {
    val reaktiveVersion = "1.1.20"
    val mvikotlin = "2.0.1"

    version = "0.1.0"

    android()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.31")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
                api("com.arkivanov.mvikotlin:mvikotlin:$mvikotlin")
                api("com.arkivanov.mvikotlin:mvikotlin-main:$mvikotlin")
                api("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$mvikotlin")
                api("com.arkivanov.mvikotlin:mvikotlin-logging:$mvikotlin")
                api("com.arkivanov.mvikotlin:keepers:$mvikotlin")
                api("com.badoo.reaktive:reaktive:$reaktiveVersion")
            }
        }

        /*val commonTest by getting {
            dependencies {
                implementation("org.json:json:20180813")
                implementation("org.jetbrains.kotlin:kotlin-test-common:1.4.31")
                implementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.31")
                implementation("org.jetbrains.kotlin:kotlin-test:1.4.31")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:1.4.31")
            }
        }*/

        val androidMain by getting {
        }
    }

    cocoapods {
        summary = "Common logic for sky dictionary"
        homepage = "https://github.com/AlexeyKorshun/SkyDictionary"
        frameworkName = "skydictionaryapi"
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java)
        .all {
            binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java)
                .all {
                    freeCompilerArgs = freeCompilerArgs.plus("-Xobjc-generics").toMutableList()

                    export("com.arkivanov.mvikotlin:mvikotlin-$iosExportSuffix:$mvikotlin")
                    export("com.arkivanov.mvikotlin:mvikotlin-main-$iosExportSuffix:$mvikotlin")
                    export("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive-$iosExportSuffix:$mvikotlin")
                    export("com.arkivanov.mvikotlin:mvikotlin-logging-$iosExportSuffix:$mvikotlin")
                    export("com.arkivanov.mvikotlin:keepers-$iosExportSuffix:$mvikotlin")
                    export("com.badoo.reaktive:reaktive:$reaktiveVersion")
                }
        }
}