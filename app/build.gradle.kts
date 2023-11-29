plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.cookbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cookbook"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //compose
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.animation:animation:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.5.4")
    implementation("androidx.compose.runtime:runtime:1.5.4")
    implementation("androidx.compose.ui:ui:1.5.4")

    //test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    //room
//    def room_version = "2.4.3"
//
//    implementation("androidx.room:room-runtime:$room_version"
//    annotationProcessor("androidx.room:room-compiler:$room_version")
//    //ksp("androidx.room:room-compiler:$room_version")
//    testimplementation("androidx.room:room-testing:$room_version"
//    implementation("androidx.room:room-paging:2.5.0-beta01"


    //room JPCE
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    annotationProcessor("androidx.room:room-compiler:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    //noinspection KaptUsageInsteadOfKsp


    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    //implementation("androidx.compose.runtime:runtime-livedata:1.2.1")

    //ViewModel Compose

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")

    ///annotationProcessor("com.google.dagger:hilt-compiler:2.48.1")
    ksp("com.google.dagger:hilt-compiler:2.48.1")

    // hiltViewModel()
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // splash screen
    //implementation("androidx.core:core-splashscreen:1.0.1")
}

hilt {
    enableAggregatingTask = true
}