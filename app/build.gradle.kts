import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}


val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.okysoft.annictim"
        minSdk = 24
        targetSdk = Dependencies.Version.targetSdkVersion.toIntOrNull()
        versionCode = versionMajor * 100 + versionMinor * 10 + versionPatch
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val propertiesFile = file(project.rootProject.file("local.properties"))
        if (propertiesFile.exists()) {
            val properties = Properties()
            properties.load(propertiesFile.inputStream())
            buildConfigField("String", "annictClientId", properties.getProperty("CLIENT_ID"))
            buildConfigField("String", "annictClientKey", properties.getProperty("CLIENT_KEY"))
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled =  false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
    lintOptions {
        isAbortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-alpha03"
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.31")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation( "androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("com.google.android.material:material:1.4.0")

    implementation("androidx.browser:browser:1.3.0")

    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("com.jakewharton.rxrelay2:rxrelay:2.1.1")

    dagger()

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.3.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    testImplementation("org.powermock:powermock-core:2.0.9")
    testImplementation("org.powermock:powermock-module-junit4:2.0.9")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.9")
    testImplementation("com.google.truth:truth:1.1.3")

    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")

    implementation("com.makeramen:roundedimageview:2.3.0")

    val nav_version = "2.3.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")


    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.5.2")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")


    implementation("androidx.viewpager2:viewpager2:1.0.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":infra"))
    implementation(project(":common"))

    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    implementation("androidx.fragment:fragment-ktx:1.3.6")

    implementation("com.apollographql.apollo:apollo-runtime:2.5.9")

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.3.1")
    // Compose Material Design
    implementation("androidx.compose.material:material:1.0.2")
    // Animations
    implementation("androidx.compose.animation:animation:1.0.2")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.2")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-beta01")
    // UI Tests
    androidTestDebugImplementation("androidx.compose.ui:ui-test-junit4:1.0.2")

    // When using a AppCompat theme
    implementation("com.google.accompanist:accompanist-appcompat-theme:0.18.0")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.18.0")

    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-alpha04")

    implementation("io.coil-kt:coil-compose:1.3.2")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.18.0")

    implementation("androidx.paging:paging-runtime-ktx:3.0.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha13")

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")

    implementation( "com.google.accompanist:accompanist-pager:0.19.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.19.0")
}
