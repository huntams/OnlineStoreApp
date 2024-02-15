pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "OnlineStoreApp"
include(":app")
include(":core:data")
include(":core:database")
include(":core:network")
include(":features:catalog")
include(":features:registration")
include(":features:profile")
include(":core:domain")
include(":core:model")
