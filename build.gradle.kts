/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn how to create Gradle builds at https://guides.gradle.org/creating-new-gradle-builds
 */

val dc2fVersion = "0.2.4-SNAPSHOT"

subprojects {
    version = dc2fVersion
}

//project(":dc2f-edit-api") {
//    apply(plugin = "maven-publish")
//}

val secretConfig = file("dc2f/_tools/secrets/build_secrets.gradle.kts")
if (secretConfig.exists()) {
    apply { from(secretConfig) }
    allprojects {
        extra["signing.secretKeyRingFile"] = "../dc2f/" + extra["signing.secretKeyRingFile"]
    }
} else {
    println("Warning: Secrets do not exist, maven publish will not be possible.")
}

tasks {
    register("publishAll") {
        subprojects.forEach {
            dependsOn("${it.name}:publish")
        }
    }
}

configure(subprojects) {
//    apply(plugin = "kotlin")
    println("testing $name ($version)")
    configurations.all {

        resolutionStrategy.dependencySubstitution {
            substitute(module("com.dc2f:dc2f")).apply {
                with(project(":dc2f"))
                because("we work with the unreleased development version")
            }
            substitute(module("com.dc2f:dc2f-edit-api")).apply {
                with(project(":dc2f-edit-api"))
                because("we work with the unreleased development version")
            }
        }
//        resolutionStrategy.dependencySubstitution.all {
//            requested.let {
//                if (it is ModuleComponentSelector && it.group == "com.dc2f") {
//                    println("substitude $it with ($dc2fVersion)")
//                    useTarget(mapOf("version" to dc2fVersion))
//                }
//            }
//        }
    }
}
