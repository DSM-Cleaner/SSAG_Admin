plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk

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
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    testImplementation(Dependency.Test.junit)

    implementation(Dependency.Coroutine.core)

    implementation(Dependency.Network.retrofit)
    implementation(Dependency.Network.gsonConverter)
    implementation(Dependency.Network.okhttp)

    implementation(Dependency.Local.dataStore)
    implementation(Dependency.Local.dataStorePreference)

    implementation(Dependency.DI.inject)
    implementation(Dependency.DI.hiltAndroid)
    kapt(Dependency.DI.hiltCompiler)

    implementation(Dependency.ThreeTenAndroidBackport.threeTenAbp)
}