enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "HomeworkProject"
include(":app")
include(":core:core-data")
include(":core:core-network")
include(":core:core-db")
include(":core:core-di")
include(":core:navigation")
include(":core:resources")
include(":features:feature-characters")
include(":features:feature-characters-description")
include(":features:feature-favorite-characters")
include(":features:feature-splash-screen")
include(":features:feature-created-characters")
