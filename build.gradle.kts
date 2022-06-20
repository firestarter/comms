plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    kotlin("jvm") version "1.7.0"
    kotlin("kapt") version "1.7.0"
}

group = "com.firestartermc"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.1.2-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:3.1.2-SNAPSHOT")
    implementation(kotlin("stdlib"))
}

tasks {
    build {
        dependsOn("shadowJar")
    }
}