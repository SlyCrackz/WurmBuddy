plugins {
    id("java")
}

group = "com.crackz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.formdev:flatlaf:3.2.1")

}

tasks.test {
    useJUnitPlatform()
}