
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.11"
}

group = "com.bienvenu"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

val javaVersion = JavaVersion.VERSION_11

tasks.withType<JavaCompile>{
    sourceCompatibility = javaVersion.toString()
    targetCompatibility = javaVersion.toString()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>{
    kotlinOptions.jvmTarget = javaVersion.toString()
}
val sshAntTask = configurations.create("sshAntTask")
dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")


    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-tomcat-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation ("org.ktorm:ktorm-core:3.4.1") //ktorm
    implementation ("mysql:mysql-connector-java:8.0.29") //mysql connector

    sshAntTask("org.apache.ant:ant-jsch:1.10.13")

}

val buildingJarFileName = "temp-server.jar"
val startingJarFileName = "server.jar"

val serverUser = "root"
val serverHost = ""
//val serverPort = 22
val serverSshKey = file("key/id_rsa")
val deleteLog =true
val lockFileName=".serverLock"

val  serviceName = "ktor-server"
val serverFolderName = "app"

ktor {
    fatJar {
        archiveFileName.set(buildingJarFileName)
    }
}

ant.withGroovyBuilder {
    "taskdef"(
        "name" to "scp",
        "classname" to "org.apache.tools.ant.taskdefs.optional.ssh.scp",
        "classpath" to configurations["sshAntTask"].asPath
    )
    "taskdef"(
        "namw" to "ssh",
        "classname" to "org.apache.tools.ant.taskdefs.optional.ssh.SSHExec",
        "classpath" to configurations["sshAntTask"].asPath
    )
}