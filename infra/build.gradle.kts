import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo")
}


android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(22)
        targetSdkVersion(Dependencies.Version.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

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
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    apollo {
        // instruct the compiler to generate Kotlin models
        generateKotlinModels.set(true)

    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Dependencies.Version.kotlin}")

    testImplementation("junit:junit:4.13.1")

    retrofit()
    coroutines()
    implementation(Dependencies.gson)
    dagger()

    implementation("com.google.dagger:hilt-android:2.31.2-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.31.2-alpha")


    implementation("com.apollographql.apollo:apollo-runtime:2.2.0")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.2.0")

    implementation(project(":data"))
    implementation(project(":common"))
}
