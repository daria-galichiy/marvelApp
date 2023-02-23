// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.toolsBuildGradle)
        classpath(libs.kotlinGradlePlugin)
        classpath(libs.kotlinSerialization)
        classpath(libs.navigationSafeArgsGradlePlugin)
    }
}

plugins {
    alias(libs.plugins.comAndroidApplicationModule).apply(false)
    alias(libs.plugins.comAndroidLibraryModule).apply(false)
    alias(libs.plugins.jetbrainsKotlinAndroid).apply(false)
    alias(libs.plugins.jetbrainsKotlinJvm).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
