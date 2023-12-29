plugins {
    java
}

group = "nl.chimpgamer.worldguardextraplaceholders"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

    maven("https://maven.enginehub.org/repo/") // WorldGuard Repository
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    compileOnly("me.clip:placeholderapi:2.11.5")

    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.8") // WorldGuard
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}