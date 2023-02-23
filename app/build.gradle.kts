plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.alfacampus.homeworkproject"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(project(":core:core-di"))
    implementation(project(":core:core-network"))
    implementation(project(":core:core-db"))
    implementation(project(":core:resources"))
    implementation(project(":features:feature-characters"))
    implementation(project(":features:feature-characters-description"))
    implementation(project(":features:feature-favorite-characters"))
    implementation(project(":features:feature-splash-screen"))
    implementation(project(":core:navigation"))

    implementation(libs.androidxCore)
    implementation(libs.androidxAppcompat)
    implementation(libs.androidMaterial)
    implementation(libs.androidxConstraint)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestJunit)
    androidTestImplementation(libs.androidxTestEspresso)

    implementation(libs.androidxLifecycleLivedata)
    implementation(libs.androidxActivity)
    implementation(libs.androidxFragment)

    implementation(libs.googleGson)
    implementation(libs.kotlinxSerializationJson)

    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)

    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesAndroid)

    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.okHttpLoggingInterceptor)

    implementation(libs.room)
    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.glide)
    kapt(libs.glideCompiler)
}
