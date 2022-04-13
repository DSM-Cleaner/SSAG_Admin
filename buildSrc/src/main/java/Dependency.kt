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
        const val composeMaterial = "androidx.compose.material:material:$${Version.jetpackCompose}"
        const val composePreview =
            "androidx.compose.ui:ui-tooling-preview:${Version.jetpackCompose}"
        const val activityCompose =
            "androidx.activity:activity-compose:${Version.activityCompose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.jetpackCompose}"
    }

    object Lifecycle {
        const val runTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
        const val mockito = "org.mockito:mockito-core:${Version.mockito}"
        const val androidJunit = "androidx.test.ext:junit:${Version.androidJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val mockitoKotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlin}"
        const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInline}"
    }
}