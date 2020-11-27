# TODO
# Get _something_ building
# Run tests
# Figure out build variants (debug, release ,... )

android_binary(
    name = "main",
    manifest = "//app/src/main:AndroidManifest.xml",
    custom_package = "com.zmarkan.app_main",
    manifest_values = {
        "minSdkVersion": "21"
    },
    deps = [
        "//app/src/main:kt_app_main",
        "//app/src/main:res_main"
        ],
)