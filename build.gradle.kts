plugins {
    kotlin("jvm") version "2.0.21"
}

group = "ro.tuiasi.pp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // GraalVM Polyglot API + JavaScript runtime (Community)
    implementation("org.graalvm.polyglot:polyglot:24.1.2")
    implementation("org.graalvm.polyglot:js-community:24.1.2")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.test {
    useJUnitPlatform()
    // GraalVM necesită acces la module JDK pentru compilatorul JIT
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
}

kotlin {
    jvmToolchain(21)
}
