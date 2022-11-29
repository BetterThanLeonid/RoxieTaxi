plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.roxiemobiletestapp"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
//            isShrinkResources = false
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
        viewBinding = true
    }
}
dependencies {
    // AndroidX
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.extra.get("lifecycleVersion") as String}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.extra.get("lifecycleVersion") as String}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra.get("lifecycleVersion") as String}")
    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.extra.get("navVersion") as String}")
    implementation("androidx.navigation:navigation-ui-ktx:${rootProject.extra.get("navVersion") as String}")

    // Network
    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra.get("retrofitVersion") as String}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra.get("retrofitVersion") as String}")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Google
    implementation("com.google.android.material:material:1.7.0")

    // Dependency Injection

    implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hiltVersion") as String}")
    implementation("androidx.hilt:hilt-work:${rootProject.extra.get("hiltWorkVersion") as String}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra.get("hiltVersion") as String}")
    kapt("androidx.hilt:hilt-compiler:${rootProject.extra.get("hiltWorkVersion") as String}")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:${rootProject.extra.get("workManagerVersion") as String}")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}