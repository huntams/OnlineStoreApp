// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}")
    }
}
plugins {
    id ("com.android.library") version "7.4.2" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false

    id ("com.google.dagger.hilt.android") version "2.48.1" apply false
}