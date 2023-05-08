plugins {
	java
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
}

group = "dev.lanka"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral();
	google()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("javax.validation:validation-api:2.0.1.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}


