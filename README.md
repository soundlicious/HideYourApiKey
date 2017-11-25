An easy method to hide your API KEY is to create an environment variable for your Key :
* [On Windows](https://kb.wisc.edu/cae/page.php?id=24500)
* [On Mac](https://stackoverflow.com/questions/7501678/set-environment-variables-on-mac-os-x-lion)
* [On Linux](https://www.cyberciti.biz/faq/set-environment-variable-linux/) (even if i'm sure you don't need it ;) )

Once you have set this Variable you need to retrieve it through your code.
The best way to do that is to create a buildConfigField in your gradle.

## create a buildConfigField in your Application Gradle

```
android {
    ....
    buildTypes {
      debug {
          buildConfigField("String", "CIA_API_KEY", "\"${System.getenv("CIA_API_KEY")}\"")
      }
      release {
          ...
          buildConfigField("String", "CIA_API_KEY", "\"${System.getenv("CIA_API_KEY")}\"")
      }
    }
    ....
}
```
### Retrieve your buildConfigField in your code

```
BuildConfig.CIA_API_KEY
```

But What about API Keys, needed in your manifest?
for that you need to create a manifestPlaceholder

## create a resValue in your Application Gradle to retrieve in Manifest
```
android {
  debug {
              resValue "string", "CIA_API_KEY_MANIFEST", "\"${System.getenv("CIA_API_KEY_MANIFEST")}\""
          }
          release {
              ...
              resValue "string", "CIA_API_KEY_MANIFEST", "\"${System.getenv("CIA_API_KEY_MANIFEST")}\""
          }
    ...
}
```

### How to retrieve it in the manifest

```
<meta-data
       android:name="MANIFEST_API_KEY"
       android:value="@String/CIA_AP_KEY_MANIFEST"/>
```

### How to get Manifest data in code

```
try {
    ApplicationInfo ai = getPackageManager().getApplicationInfo( this.getPackageName(), PackageManager.GET_META_DATA);
    Bundle metaData = ai.metaData;
    if(metaData != null){
        apiCodeManifestString = (String)metaData.get("MANIFEST_API_KEY");
        apiCodeManifest.setText("My Manifest API Key is : " + apiCodeManifestString);
    }
} catch (PackageManager.NameNotFoundException e) {
    e.printStackTrace();
}
```
