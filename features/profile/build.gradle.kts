plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.profile"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation ("androidx.paging:paging-runtime-ktx:${Versions.paging}")
    implementation ("androidx.room:room-runtime:${Versions.room}")
    implementation ("androidx.room:room-ktx:${Versions.room}")
    implementation ("io.coil-kt:coil:${Versions.coil}")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("androidx.activity:activity-ktx:${Versions.activityKtx}")
    implementation ("androidx.recyclerview:recyclerview:${Versions.recyclerView}")
    implementation ("com.google.dagger:dagger:${Versions.dagger}")
    kapt ("com.google.dagger:dagger-compiler:${Versions.dagger}")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    // reflection-free flavor
    implementation ("com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBinding}")
    implementation ("androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}")
    implementation ("androidx.navigation:navigation-ui-ktx:${Versions.navVersion}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraint}")
    testImplementation("junit:junit:${Versions.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.extJunit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
}