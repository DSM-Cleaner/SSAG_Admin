object Dependency {

    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val androidKtx = "androidx.activity:activity-ktx:${Version.androidKtx}"

    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Version.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
    }

    object UI {
        const val compose = "androidx.compose.ui:ui:${Version.jetpackCompose}"
        const val composeMaterial = "androidx.compose.material:material:${Version.jetpackCompose}"
        const val composePreview =
            "androidx.compose.ui:ui-tooling-preview:${Version.jetpackCompose}"
        const val activityCompose =
            "androidx.activity:activity-compose:${Version.activityCompose}"
        const val composeNumberPicker =
            "com.chargemap.compose:numberpicker:${Version.composeNumberPicker}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.jetpackCompose}"
        const val composeConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Version.composeConstraintLayout}"
    }

    object Lifecycle {
        const val runTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
        const val mockito = "org.mockito:mockito-core:${Version.mockito}"
        const val mockitoKotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlin}"
        const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInline}"
    }

    object DI {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        const val inject = "javax.inject:javax.inject:1"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltCompose}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    }

    object Local {
        const val dataStore = "androidx.datastore:datastore:${Version.dataStore}"
        const val dataStorePreference =
            "androidx.datastore:datastore-preferences:${Version.dataStore}"
    }

    object Mvi {
        const val orbitCore = "org.orbit-mvi:orbit-core:${Version.orbit}"
        const val orbitViewModel = "org.orbit-mvi:orbit-viewmodel:${Version.orbit}"
    }

    object ThreeTenAndroidBackport {
        const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Version.threeTenAbp}"
    }
}