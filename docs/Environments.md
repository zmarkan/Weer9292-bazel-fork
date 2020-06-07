# Environments

### BuildTypes
- Release is used for production only!!!
- Acceptance can be used for demo's and closely resembles the release app.
This means it also includes ProGuard obfuscation, which lacks in the debug buildType.
- Debug is used to develop with. ***Try to use this BuildConfig as much as possible*** to prevent using the production environment (which pollutes analytics!).
Compared to Acceptance, Debug builds faster (no ProGuard obfuscation & optimalization) and is debuggable (incl. logs).
Debug also includes [mocked data](../app/src/main/java/nl/tcilegnar/weer9292/network/util/Mocks.kt).

##### Important note for testing:
- Testing on Debug is adviced, this is nearly the same as testing the app on a Release buildType.
However, ProGuard obfuscation can cause issues unnoticable on Debug:
Obfuscation makes classes 'unreadable', resulting in a crash if these are used for reflection.
This is the case for eg. request/response models used by Retrofit and models passed as args for Androidx.navigation.
To be on the safe side, always test on the Acceptance buildType (which also performs ProGuard obfuscation) before releasing,
especially when new models were added, or existing ones were moved out of the packages excluded by [ProGuard-rules](../app/proguard-rules.pro).



### ProductFlavors
- There are currently no product flavors yet.
