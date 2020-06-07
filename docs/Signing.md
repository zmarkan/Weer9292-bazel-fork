# Signing

## Debug
The Debug BuildConfig is signed with a Debug.keystore saved in this project (so all developers will use the same one).
This prevents other developers form having to re-install the app if running/updating it on the same device.



## Release
Release builds are signed using a `release.keystore`. Due to security reasons, the keystore in this project is a dummy. Signing release can be done as follows:

1) Create a `keystore.properties` file somewhere locally, for example in the [keystores](../keystores) directory.
The contents should be similar to [this file](../keystores/dummy_keystore.properties), with the correct passwords and location of the release keystore.
2) Set the location of the `keystorePropertiesPath9292Android` in the global (local) `gradle.properties` file to the location of the `keystore.properties` file you just created.
3) Finally, add the `release.keystore` to the location defined at `signing.store.file` in your `keystore.properties`.
This location could also be the [keystores](../keystores) directory.



## Notes:
- Passwords and the `release.keystore` can be requested from Patrick.
- The values in the global `gradle.properties` file will override the project values located [here](../gradle.properties).
The global `gradle.properties` is located in the `USER_HOME/.gradle` directory, not in this project, because the
location of the `keystore.properties` can be different for each developer, and we don't want to store these in source control.
- The `*.keystore` and `keystore.properties` (incl. passwords) are already added to GitIgnore,
to prevent saving them under source control!
