import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.apollographql.apollo")
}


android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(22)
        targetSdkVersion(Dependencies.Version.targetSdkVersion)

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    apollo {
        // instruct the compiler to generate Kotlin models
        generateKotlinModels.set(true)

    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.31")

    testImplementation("junit:junit:4.13.2")

    retrofit()
    coroutines()
    implementation(Dependencies.gson)
    dagger()

    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")


    implementation("com.apollographql.apollo:apollo-runtime:2.5.9")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.5.9")

    implementation(project(":data"))
    implementation(project(":common"))
}
