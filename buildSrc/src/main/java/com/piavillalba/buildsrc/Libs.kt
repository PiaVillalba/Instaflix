package com.piavillalba.buildsrc

object Libs {

    // Kotlin
    const val kotlinJDK = "org.jetbrains.kotlin:kotlin-stdlib:" + LibsVersions.kotlin_version

    // AndroidX
    const val appCompat = "androidx.appcompat:appcompat:" + LibsVersions.appCompat_version
    const val appCompatV4 = "androidx.legacy:legacy-support-v4:" + LibsVersions.legacy_support_version
    const val androidMaterial = "com.google.android.material:material:" + LibsVersions.material_version
    const val constraint = "androidx.constraintlayout:constraintlayout:" + LibsVersions.constraint_version
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:" + LibsVersions.navigation_version
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:" + LibsVersions.navigation_version

    // UI
    const val skeleton = "com.ethanhua:skeleton:" + LibsVersions.skeleton_version
    const val shimmer = "io.supercharge:shimmerlayout:" + LibsVersions.shimmer_version
    const val glide = "com.github.bumptech.glide:glide:" + LibsVersions.glide_version

    //Coroutines
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + LibsVersions.coroutines_version

    // Dagger-hilt
    const val hilt_android = "com.google.dagger:hilt-android:" + LibsVersions.hilt_version
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:" + LibsVersions.hilt_version

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:" + LibsVersions.retrofit_version
    const val retrofit_converter = "com.squareup.retrofit2:converter-gson:" + LibsVersions.retrofit_version
    const val okhttp = "com.squareup.okhttp3:okhttp:" + LibsVersions.okhttp_version
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:" + LibsVersions.okhttp_version
    const val gson = "com.google.code.gson:gson:" + LibsVersions.gson_version

    //Testing
    const val junit = "junit:junit:" + LibsVersions.junit_version
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:" + LibsVersions.coroutines_version
    const val mockk = "io.mockk:mockk:" + LibsVersions.mockk_version
    const val testRules = "androidx.test:rules:" + LibsVersions.testRules_version
}