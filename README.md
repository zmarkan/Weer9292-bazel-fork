# weer9292
A simple weather app created for some nice guys at 9292, to check whether they can lunch outside without getting soaked

*Important note for the 9292 devs:*
Run the Acceptance build to test the app.
If the API calls fail because the limit of calls is reached, then Debug can be used, which uses [mocked data](app/src/main/java/nl/tcilegnar/weer9292/network/model/Mocks.kt).

The app was developed using a Nexus 5X emulator in portrait mode and not extensively tested on other devices / tablets / orientations.
It should scale sufficiently to most other mobiles in portrait though.



## Setup
- The Debug and Acceptance builds can be run without additional setup.
- Release builds require signing - so some additional manual setup - which is described [here](./docs/Signing.md)
- Similarly it's possible to use your own local `RAPID_API_KEY`, or simply change it [here](gradle.properties) temporarily.



## Documentation
Important info about the project's workflow and maintenance (like code style & automated testing) are described
[here](./docs/Workflow_And_Maintenance.md). ***Make sure to read these before working on this project!***
All other documentation under source control can be found [here](./docs).
