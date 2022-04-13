object Dependency {

    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Version.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
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