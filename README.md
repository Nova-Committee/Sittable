# Sittable

## Introduction

Sittable allows users to make specific blocks sittable.

## Import as a dependency

```groovy
repositories {
    //...
    maven {
        url "https://maven.nova-committee.cn/releases"
    }
}
```

### ForgeGradle 5+

```groovy
dependencies {
    //...
    implementation(project.dependencies.create("committee.nova.sittable.forge:sittable-${minecraft_version}:${mod_version}") {
        transitive = false
    })
}
```

### Forge1.7.10 / ForgeGradle1.2 by Anatawa / Gradle5.6.4

```groovy
dependencies {
    //...
    implementation(project.dependencies.create("committee.nova.sittable.forge:sittable-1.7.10:${mod_version}:dev") {
        transitive = false
    })
}
```

## Usage

Subscribe **SittableRegisterEvent**, and then call **registerSittable**.