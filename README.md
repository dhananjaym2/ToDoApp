------DEBUGGING EXERCISE--------

1. Check your build.gradle file to verify there are no compilation errors.

You may need to update this file depending on what SDK verison you have installed and
what Build Tools you have installed.  The current configuration relies on SDK 18
and Build Tools 19.1.0:

```
android {
    compileSdkVersion 18
    buildToolsVersion "19.1.0"
```

2. Compile and run.

3. The app is supposed to be able to save and load items from a file called todo.txt.  Find out
why it's not working.


----------FEATURE BUILDING-----------

1.  Create 2 fragments on single screen.

2. Show a map on first fragment. If a user clicks any where on map show a marker there.

3. Display the address of selected location on second fragment.