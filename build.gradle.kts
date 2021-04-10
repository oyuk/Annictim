// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlinVersion by extra { "1.4.32" }
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.3")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.31.2-alpha")
        classpath("com.apollographql.apollo:apollo-gradle-plugin:2.2.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
