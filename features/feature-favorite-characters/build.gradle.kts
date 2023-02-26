plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

dependencies {
    implementation(project(":core:core-data"))
    implementation(project(":core:core-di"))
    implementation(project(":core:core-db"))
    implementation(project(":core:resources"))
    implementation(project(":core:navigation"))

    implementation(libs.androidxCore)
    implementation(libs.androidxAppcompat)
    implementation(libs.androidMaterial)
    implementation(project(mapOf("path" to ":core:navigation")))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestJunit)
    androidTestImplementation(libs.androidxTestEspresso)

    implementation(libs.androidxFragment)
    implementation(libs.androidxLifecycleViewmodel)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesCoreJvm)
    implementation(libs.kotlinxCoroutinesAndroid)

    implementation(libs.kotlinxSerializationJson)
    implementation(libs.googleGson)

    implementation(libs.room)
    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)

    implementation(libs.glide)
    kapt(libs.glideCompiler)

    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
}
