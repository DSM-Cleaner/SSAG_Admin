plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {

        applicationId = "com.ssag.ssag_admin"
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.jetpackCompose
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependency.appcompat)
    implementation(Dependency.coreKtx)
    implementation(Dependency.androidKtx)

    implementation(Dependency.UI.compose)
    implementation(Dependency.UI.composeMaterial)
    implementation(Dependency.UI.composePreview)
    implementation(Dependency.UI.activityCompose)
    debugImplementation(Dependency.UI.composeTooling)

    implementation(Dependency.DI.hiltAndroid)
    implementation(Dependency.DI.hiltCompose)
    kapt(Dependency.DI.hiltCompiler)

    implementation(Dependency.Lifecycle.runTime)

    implementation(Dependency.Network.retrofit)
    implementation(Dependency.Network.gsonConverter)
    implementation(Dependency.Network.okhttp)
    implementation(Dependency.Network.loggingInterceptor)

    implementation(Dependency.ThreeTenAndroidBackport.threeTenAbp)
}