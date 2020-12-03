# Forgelin
Fork of [Shadowfact's Forgelin](https://github.com/shadowfacts/Forgelin).

## Additions
- Shades the Kotlin standard library, runtime, coroutines-core, and reflect libraries so you don't have to.
- Shades kotlin compiler and includes a jsr-223 script engine so you can write CNPC scripts with kotlin.
- Provides a Forge `ILanguageAdapter` for using Kotlin `object` classes as your main mod class.

## Build
After build, open the jar with WinRAR/7Zip and delete `META-INF/versions` folder,
or your artifact will not be loaded by FML.

**Why:** Those folder contains class files that is valid only after Java9+. and when FML passes these classes to coremods,
 the `ClassReader`s inside those coremods will throw exceptions, causing the jar to be thought corrupted.


## Usage
```groovy
repositories {
	jcenter()
	maven {
		url "http://maven.shadowfacts.net/"
	}
}

dependencies {
	compile group: "net.shadowfacts", name: "Forgelin", version: "LATEST_VERSION"
}
```

All versions can be seen [here](http://maven.shadowfacts.net/net/shadowfacts/Forgelin/).

**Note:** You must have the `jcenter()` call in your `repositories` block. JCenter is used to host the Kotlin coroutines libraries.