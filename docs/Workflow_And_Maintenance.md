# Workflow & maintenance

## Environments
Develop with the Debug buildType only.
For more information how to handle the different environments in the app, see [Environments](./Environments.md)



## Code style
The project includes some .idea files like [codeStyles](../.idea/codeStyles) to maintain the same code styling throughout the project.

*Highly recommended*  
- Either use the Android Studio's VCS commit tool and turn on `reformat code` and `optimize imports` `before commit`
- Or make sure to turn on the `format on save` setting in Android Studio.

##### Format on save
Create a macro that includes the actions `ReformatCode`, `OptimizeImports` and `SaveAll` on a Ctrl-S hotkey combo to easily maintain
code formatting at all time!
Start with `Edit > Macros > Start Macro Recording`, followed by all hotkeys for the actions above, and finally `Stop Macro Recording`



## Automated testing
All automated tests are located in packages that start with either `test` or `androidTest`. The latter contains
tests that depend on the Android framework (eg. UI tests & other Context related tests).

The tests can be BuildType or ProductFlavor specific: eg. some Debug specific tests are located in the package
`androidTestDebug`. In the same way tests can also be run for `androidTestMyFlavorDebug`, if there will be any flavor
support in the future.



## Continuous Integration
At the moment of writing no CI has been setup.

Make sure the buildserver hasn't only installed all necessary SDK components,
but also an emulator to run the instrumented tests on.

Note: instrumented tests currently don't run on the Acceptance and Release buildConfig yet.
[This stackoverflow post](https://stackoverflow.com/questions/59153826/active-build-variant-does-not-have-a-test-artifact) might give a clue why this fails.



## Source control
Source control is maintained using git in [GitHub](https://github.com/PatrickKuijpers/Weer9292)

[This](https://nvie.com/posts/a-successful-git-branching-model/) git branching model describes how to handle branching properly.
Adhere to this as much as practically possible, especially during releases.



## Releases
At the moment of writing the app hasn't been released yet and no CD is setup.

Both [Changelog](./CHANGELOG.md) and the [Release notes](./Release_Notes.md) should be updated before finishing a release branch.
Don't forget to merge the release branch to both master and develop, checkout master, and tag this merge commit with the version number when creating the `app bundle`.
Make sure [Release signing](./Signing.md) is setup locally (and eventually on the buildserver),
and create an `app bundle` to upload to the [Google Play Console](https://developer.android.com/distribute/console)

