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

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.0.1"
    }
}

kotlin {
    val reaktiveVersion = "1.1.18"
    val settingsVersion = "0.6.2"
    val serializationVersion = "1.0.1"
    val mvikotlin = "2.0.0"

    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("com.russhwolf:multiplatform-settings:$settingsVersion")
                api("com.arkivanov.mvikotlin:mvikotlin:$mvikotlin")
                api("com.arkivanov.mvikotlin:mvikotlin-commonMain:$mvikotlin")
                api("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$mvikotlin")
                api("com.arkivanov.mvikotlin:mvikotlin-logging:$mvikotlin")
                api("com.arkivanov.mvikotlin:keepers:$mvikotlin")
                api("com.badoo.reaktive:reaktive:$reaktiveVersion")
            }
        }

        val androidMain by getting {
            dependencies {
                api("com.arkivanov.mvikotlin:mvikotlin-extensions-androidx:$mvikotlin")
            }
        }
    }
}