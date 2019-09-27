meta project to make it easier to publish dc2f projects at the same time

* com.dc2f:dc2f
* com.dc2f:dc2f-common
* com.dc2f:dc2f-edit-api


# New release:

```bash
git submodule foreach git pull
# Increment dc2fVersion in build.gradle
./gradlew publish
```

