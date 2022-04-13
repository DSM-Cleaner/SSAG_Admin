plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {

        applicationId = "com.ssag.ssag_admin"
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
    }

    buildTypes {
        release {
            minifyEnabled false
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
        compose true
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
}

dependencies {


    implementation(Dependency.appcompat)
    implementation(Dependency.coreKtx)
    implementation(Dependency.androidKtx)

    implementation(Dependency.UI.compose)
    implementation(Dependency.UI.composeMaterial)
    implementation(Dependency.UI.composePreview)
    implementation(Dependency.UI.activityCompose)
    debugImplementation(Dependency.UI.composeTooling)

    implementation(Dependency.Lifecycle.runTime)
}