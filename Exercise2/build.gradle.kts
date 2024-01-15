plugins {
    id("java")
    id("application")
}

group = ""
version = "1.0-SNAPSHOT"

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "Main"
}

tasks.jar{
    manifest {
        attributes["Main-Class"] = "Main"
    }
}
