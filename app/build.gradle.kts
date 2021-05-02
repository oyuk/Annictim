import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("com.google.android.gms.oss-licenses-plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}


val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId("com.okysoft.annictim")
        minSdkVersion(24)
        targetSdkVersion(Dependencies.Version.targetSdkVersion)
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
    }
    androidExtensions {
        isExperimental = true
        features = setOf("parcelize")
    }
    lintOptions {
        isAbortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Dependencies.Version.kotlin}")
    implementation( "androidx.appcompat:appcompat:1.2.0")
    implementation( "androidx.cardview:cardview:1.0.0")
    implementation( "androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.browser:browser:1.3.0")

    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("io.reactivex.rxjava2:rxkotlin:2.2.0")
    implementation("com.jakewharton.rxrelay2:rxrelay:2.0.0")

    dagger()

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.3.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")

    testImplementation("org.powermock:powermock-core:2.0.0-beta.5")
    testImplementation("org.powermock:powermock-module-junit4:2.0.0-beta.5")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.0-beta.5")
    testImplementation("com.google.truth:truth:1.0.1")

    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")

    implementation("com.makeramen:roundedimageview:2.3.0")

    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0")
    implementation("android.arch.navigation:navigation-ui-ktx:1.0.0")

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.4.2")

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

    implementation("com.google.dagger:hilt-android:2.31.2-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.31.2-alpha")

    implementation("androidx.fragment:fragment-ktx:1.3.2")

    implementation("com.apollographql.apollo:apollo-runtime:2.2.0")
}
