import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    id("app.cash.sqldelight")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            // Driver Database untuk Android
            implementation("app.cash.sqldelight:android-driver:2.0.1")

            // Integrasi Koin di Android
            implementation("io.insert-koin:koin-android:3.5.3")
            implementation("io.insert-koin:koin-androidx-compose:3.5.3")
        }

        iosMain.dependencies {
            implementation("app.cash.sqldelight:native-driver:2.0.1")
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Multiplatform Settings (DataStore)
            implementation("com.russhwolf:multiplatform-settings:1.1.1")
            implementation("com.russhwolf:multiplatform-settings-coroutines:1.1.1")
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")

            // SQLDelight Runtime & Coroutines
            implementation("app.cash.sqldelight:runtime:2.0.1")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
            implementation(compose.materialIconsExtended)

            // Koin Core & Compose
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("io.insert-koin:koin-compose:1.1.2")
        }

        // TAHAP 1: SETUP DEPENDENCIES TESTING
        commonTest.dependencies {
            implementation(libs.kotlin.test) 
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") 
            implementation("app.cash.turbine:turbine:1.0.0") 
            implementation("io.insert-koin:koin-test:3.5.3") 
            // Tambahkan library test untuk Multiplatform Settings
            implementation("com.russhwolf:multiplatform-settings-test:1.1.1")
        }

        // 2. TAMBAHKAN BLOK BARU INI KHUSUS UNTUK MOCKK (Android Unit Test)
        val androidUnitTest by getting {
            dependencies {
                implementation("io.mockk:mockk:1.13.9") // Pindahkan MockK ke sini!
            }
        }

        val androidInstrumentedTest by getting {
            dependencies {
                // Library untuk testing UI Jetpack Compose di Android
                implementation("androidx.compose.ui:ui-test-junit4:1.6.1")
            }
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.forkaton.pemob7_123140020")
        }
    }
}

android {
    namespace = "com.forkaton.pemob7_123140020"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.forkaton.pemob7_123140020"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // 🌟 TAMBAHAN UNTUK UI TEST: Menentukan test runner
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
    // 🌟 TAMBAHAN UNTUK UI TEST: Manifest khusus debugging & testing
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.1")
}