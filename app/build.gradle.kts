plugins {

    // Android Application
    alias(libs.plugins.android.application)

    // Kotlin Android
    alias(libs.plugins.jetbrains.kotlin.android)

    // Kotlin Kapt
    kotlin("kapt")

    // Hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.green.kamchatka"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.green.kamchatka"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        // View Binding
        viewBinding = true
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)

    // App Compat
    implementation(libs.androidx.appcompat)

    // Material
    implementation(libs.material)

    // Activity
    implementation(libs.androidx.activity)

    // Constraint Layout
    implementation(libs.androidx.constraintlayout)

    // JUnit
    testImplementation(libs.junit)

    // Androidx JUnit
    androidTestImplementation(libs.androidx.junit)

    // Espresso Core
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Retrofit
    implementation(libs.retrofit)

    // Converter Gson
    implementation(libs.converter.gson)

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // OkHttp
    implementation(libs.okhttp)

    // Yandex MapKit
    implementation("com.yandex.android:maps.mobile:4.6.1-lite")

    implementation("com.github.bumptech.glide:glide:4.16.0")
}