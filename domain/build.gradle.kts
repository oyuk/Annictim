plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(22)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Dependencies.Version.kotlin}")

    testImplementation("junit:junit:4.13.1")

    implementation(project(":data"))
    implementation(project(":infra"))
    implementation(project(":common"))

    compileOnly("javax.annotation:jsr250-api:1.0")
    implementation("com.google.dagger:dagger:${Dependencies.Version.daggerVersion}")
    implementation("com.google.dagger:dagger-android:${Dependencies.Version.daggerVersion}")
    implementation("com.google.dagger:dagger-android-support:${Dependencies.Version.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Dependencies.Version.daggerVersion}")
    kapt("com.google.dagger:dagger-android-processor:${Dependencies.Version.daggerVersion}")

    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.4.1")
    implementation("com.squareup.retrofit2:retrofit:2.8.2")
    implementation("com.squareup.retrofit2:converter-gson:2.8.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.0")


    implementation("com.google.dagger:hilt-android:2.31.2-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.31.2-alpha")

    implementation("com.apollographql.apollo:apollo-runtime:2.2.0")
}
