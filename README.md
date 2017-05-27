# CrashReport
A minimal library for Android which captures uncaught exceptions.


### Setup

1. Add the CrashReport library as a dependency to your build.gradle, as documented on [JitPack](https://jitpack.io/#fractalwrench/crashreport/-SNAPSHOT)

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```
dependencies {
    compile 'com.github.fractalwrench:crashreport:-SNAPSHOT'
}
```

2. Sync your gradle files.

3. Create a class which extends `Application`, and setup your manifest as below:

```
<application
        android:name=".MyApp"
        ... />
```

4. Initialise the CrashReport library so that it automatically catches and reports exceptions.
```
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialises automatic reporting of uncaught exceptions.
        CrashReporter.initialise();
    }

}
```


### Customisation
To override the default Error Handling behaviour, initialise the library as below:

```
CrashReporter.initialiseWithErrorHandler(new ErrorHandler() {
    @Override
    public void onError(Thread thread, Throwable throwable) {
        // TODO handle error
    }
});
```
This allows for custom behaviour, such as remotely logging errors via the network to a web server, or displaying custom UI to the user depending on what exception was thrown.

### Tests
The following command will execute the library's unit tests: ```./gradlew test```

