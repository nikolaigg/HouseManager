plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.h2database:h2:2.1.214")
    implementation ("org.hibernate.orm:hibernate-core:6.6.13.Final")
    implementation ("org.postgresql:postgresql:42.7.2")
    implementation ("jakarta.persistence:jakarta.persistence-api:3.1.0")
    testImplementation("org.mockito:mockito-core:5.17.0")
}

tasks.test {
    useJUnitPlatform()
}