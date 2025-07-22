dependencies {
    implementation ("ch.qos.logback:logback-classic")
    implementation ("com.google.guava:guava")
    implementation ("org.apache.commons:commons-lang3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.assertj:assertj-core")
}
