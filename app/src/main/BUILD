load("@rules_jvm_external//:defs.bzl", "artifact")
# load("@rules_android//android:rules.bzl", "android_library")
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_android_library")

package(
    default_visibility = ["//app/src:__subpackages__"],
)

android_library(
    name = "res_main",
    manifest = "AndroidManifest.xml",
    custom_package = "com.zmarkan.app_main",
    resource_files = glob(["res/**"]),
    enable_data_binding = False,
    deps = [
        artifact("com.google.android.material:material"),
        artifact("androidx.vectordrawable:vectordrawable"),
        artifact("androidx.constraintlayout:constraintlayout"),
        artifact("androidx.navigation:navigation-fragment"),
        artifact("androidx.navigation:navigation-fragment-ktx"),
        artifact("androidx.navigation:navigation-ui"),
        artifact("androidx.navigation:navigation-ui-ktx"),
        # artifact("androidx.navigation:navigation-fragment"),
        # artifact("androidx.navigation:navigation-fragment"),
        # artifact("androidx.navigation:navigation-fragment"),
    ]
)

kt_android_library(
    name = "kt_app_main",
    srcs = glob(["java/**/*.kt"]),
    deps = [
        ":res_main",
        artifact("androidx.appcompat:appcompat"),
        artifact("androidx.fragment:fragment"),
        artifact("androidx.core:core"),
        artifact("androidx.lifecycle:lifecycle-runtime"),
        artifact("androidx.lifecycle:lifecycle-viewmodel"),
        artifact("androidx.lifecycle:lifecycle-common"),
        artifact("androidx.drawerlayout:drawerlayout"),
        artifact("org.jetbrains.kotlinx:kotlinx-coroutines-core"),
        artifact("org.jetbrains.kotlinx:kotlinx-coroutines-android"),
    ]
)