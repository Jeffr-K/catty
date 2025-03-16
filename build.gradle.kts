plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.spring") version libs.versions.kotlin.get()
    id("org.springframework.boot") version libs.versions.springBoot.get()
    id("io.spring.dependency-management") version libs.versions.dependencyManagement.get()
    id("org.hibernate.orm") version libs.versions.hibernate.get()
    id("org.graalvm.buildtools.native") version libs.versions.graalvmNative.get()
    id("org.springframework.cloud.contract") version libs.versions.springCloudContract.get()
    id("org.asciidoctor.jvm.convert") version libs.versions.asciidoctor.get()
    kotlin("plugin.jpa") version libs.versions.kotlin.get()
}

group = "com.oscar"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    implementation(libs.springBoot.starter.actuator)
    implementation(libs.springBoot.starter.amqp)
    implementation(libs.springBoot.starter.batch)
    implementation(libs.springBoot.starter.cache)
    implementation(libs.springBoot.starter.dataJpa)
    implementation(libs.springBoot.starter.jdbc)
    implementation(libs.springBoot.starter.jooq)
    implementation(libs.springBoot.starter.mail)
    implementation(libs.springBoot.starter.quartz)
    implementation(libs.springBoot.starter.validation)
    implementation(libs.springBoot.starter.web)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.kotlin.reflect)
    implementation(libs.spring.rabbit.stream)
    implementation(libs.springCloud.config.server)
    implementation(libs.springModulith.events.api)
    implementation(libs.springModulith.starter.core)
    implementation(libs.springModulith.starter.jpa)

    developmentOnly(libs.springBoot.devtools)

    runtimeOnly(libs.micrometer.registry.prometheus)
    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.springModulith.actuator)
    runtimeOnly(libs.springModulith.events.amqp)
    runtimeOnly(libs.springModulith.observability)

    annotationProcessor(libs.springBoot.configuration.processor)

    testImplementation(libs.springBoot.starter.test)
    testImplementation(libs.springBoot.testcontainers)
    testImplementation(libs.unboundid.ldapsdk)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.spring.rabbit.test)
    testImplementation(libs.spring.batch.test)
    testImplementation(libs.springCloud.starter.contract.stub.runner)
    testImplementation(libs.springCloud.starter.contract.verifier)
    testImplementation(libs.springModulith.starter.test)
    testImplementation(libs.spring.restdocs.mockmvc)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.testcontainers.rabbitmq)

    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

hibernate {
    enhancement {
        enableAssociationManagement = true
    }
}

contracts {
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.contractTest {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}