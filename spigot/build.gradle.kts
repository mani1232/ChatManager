plugins {
    id("chatmanager-spigot")

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.h1dd3nxn1nja.chatmanager"
version = "3.9.0"
description = "The kitchen sink of Chat Management."

val buildNumber: String? = System.getenv("BUILD_NUMBER")

val jenkinsVersion = "${project.version}-b$buildNumber"

tasks {
    shadowJar {
        if (buildNumber != null) {
            archiveFileName.set("${rootProject.name}-[v${jenkinsVersion}]-Spigot.jar")
        } else {
            archiveFileName.set("${rootProject.name}-[v${project.version}]-Spigot.jar")
        }

        listOf(
            "org.bstats"
        ).forEach {
            relocate(it, "${rootProject.group}.plugin.lib.$it")
        }

        doLast {
            copy {
                from("build/libs/${rootProject.name}-[v${project.version}]-Spigot.jar")
                into(rootProject.layout.projectDirectory.dir("builds"))
            }
        }
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to project.group,
                "version" to project.version,
                "description" to project.description
            )
        }
    }
}