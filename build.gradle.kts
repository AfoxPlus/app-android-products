buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven { setUrl("https://jitpack.io") }
        maven {
            setUrl("https://maven.pkg.github.com/AfoxPlus/app-android-uikit")
            credentials {
                username = findProperty("REPO_USERID") as String? ?: System.getenv("REPO_USERID")
                password = findProperty("REPO_TOKEN") as String? ?: System.getenv("REPO_TOKEN")
            }
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
        classpath("org.jacoco:org.jacoco.core:0.8.7")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.7.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven { setUrl("https://jitpack.io") }
        maven {
            setUrl("https://maven.pkg.github.com/AfoxPlus/app-android-uikit")
            credentials {
                username = findProperty("REPO_USERID") as String? ?: System.getenv("REPO_USERID")
                password = findProperty("REPO_TOKEN") as String? ?: System.getenv("REPO_TOKEN")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}