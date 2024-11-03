plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "WWDT"
include("shared_kernel")
include("auth")
include("workspace")
