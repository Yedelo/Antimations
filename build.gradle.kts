import dev.deftu.gradle.utils.GameSide

val oneconfigVersion: String by project
val oneconfigWrapperVersion: String by project
val devAuthVersion: String by project

version = properties["mod.version"]!!

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven("https://repo.polyfrost.cc/releases")
    maven("https://repo.spongepowered.org/repository/maven-public")
    maven("https://repo.hypixel.net/repository/Hypixel/")
}

plugins {
    java
    val dgt = "2.73.0"
    id("dev.deftu.gradle.tools") version dgt
    for (tool in listOf(
        "java",
        "minecraft.loom",
        "bloom",
        "resources",
        "shadow"
    )) id("dev.deftu.gradle.tools.$tool") version dgt
}

dependencies {
    implementation("cc.polyfrost:oneconfig-wrapper-launchwrapper:$oneconfigWrapperVersion")
    shade("cc.polyfrost:oneconfig-wrapper-launchwrapper:${oneconfigWrapperVersion}")
    compileOnly("cc.polyfrost:oneconfig-${mcData.version}-${mcData.loader}:$oneconfigVersion")
    compileOnly("org.spongepowered:mixin:0.7.11-SNAPSHOT")
}

toolkitLoomHelper {
    disableRunConfigs(GameSide.SERVER)

    useTweaker("cc.polyfrost.oneconfig.loader.stage0.LaunchWrapperTweaker")
    useForgeMixin("antimations")
    useMixinRefMap("antimations")

    useDevAuth(devAuthVersion)
    useArgument("--version", "Antimations", GameSide.BOTH)
    val resourcePackDir: String? = System.getenv("minecraft.resourcePackDir")
    if (!resourcePackDir.isNullOrBlank()) {
        println("Using resource pack directory $resourcePackDir from environment variable minecraft.resourcePackDir")
        useArgument("--resourcePackDir", resourcePackDir, GameSide.BOTH)
    }
}

tasks {
    jar {
        archiveFileName = "Antimations-$version+${mcData}.jar"
        manifest.attributes(mapOf(
            "ModSide" to "CLIENT"
        ))
    }
}