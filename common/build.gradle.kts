plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.30"
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

kotlin {
    val reaktiveVersion = "1.1.20"
    val mvikotlin = "2.0.1"

    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.4.30")
                api("com.arkivanov.mvikotlin:mvikotlin:$mvikotlin")
                implementation("com.arkivanov.mvikotlin:mvikotlin-main:$mvikotlin")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$mvikotlin")
                implementation("com.arkivanov.mvikotlin:mvikotlin-logging:$mvikotlin")
                implementation("com.arkivanov.mvikotlin:keepers:$mvikotlin")
                implementation("com.badoo.reaktive:reaktive:$reaktiveVersion")
            }
        }

        val androidMain by getting {
        }
    }
}