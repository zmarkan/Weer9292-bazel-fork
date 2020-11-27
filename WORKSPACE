android_sdk_repository(
    name="androidsdk",
    api_level = 29,
    build_tools_version = "29.0.3"
)


load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")


kotlin_version = "1.4.20"
kotlin_release_sha = "11db93a4d6789e3406c7f60b9f267eba26d6483dcd771eff9f85bb7e9837011f"
rules_kotlin_compiler_release = {
    "urls": [
    "https://github.com/JetBrains/kotlin/releases/download/v{v}/kotlin-compiler-{v}.zip".format(v = kotlin_version),
    ],
    "sha256": kotlin_release_sha,
}

rules_kotlin_version = "legacy-1.4.0-rc4"
rules_kotlin_sha = "9cc0e4031bcb7e8508fd9569a81e7042bbf380604a0157f796d06d511cff2769"
http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % rules_kotlin_version],
    sha256 = rules_kotlin_sha,
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")
kotlin_repositories(compiler_release = rules_kotlin_compiler_release)
kt_register_toolchains() # to use the default toolchain, otherwise see toolchains below

RULES_JVM_EXTERNAL_TAG = "2.2"
RULES_JVM_EXTERNAL_SHA = "f1203ce04e232ab6fdd81897cf0ff76f2c04c0741424d192f28e65ae752ce2d6"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

navigation_version = '2.2.2'
arch_lifecycle_version = '2.2.0'
retrofit_version = "2.6.1"
dagger_version = '2.25.3'

# load(
#     "@dagger//:workspace_defs.bzl",
#     "DAGGER_ANDROID_ARTIFACTS",
#     "DAGGER_ANDROID_REPOSITORIES"
# )

maven_install(
    artifacts = [
    # DAGGER_ANDROID_ARTIFACTS,

    "androidx.appcompat:appcompat:1.1.0",
    "androidx.core:core-ktx:1.3.0",
    "com.google.android.material:material:1.1.0",
    "androidx.constraintlayout:constraintlayout:1.1.3",
    "androidx.vectordrawable:vectordrawable:1.1.0",
    "androidx.preference:preference-ktx:1.1.1",

    "androidx.navigation:navigation-fragment:%s" % navigation_version,
    "androidx.navigation:navigation-fragment-ktx:%s" % navigation_version,
    "androidx.navigation:navigation-ui:%s" % navigation_version,
    "androidx.navigation:navigation-ui-ktx:%s" % navigation_version,
    "androidx.lifecycle:lifecycle-extensions:%s" %arch_lifecycle_version,
    
    "com.squareup.retrofit2:retrofit:%s" % retrofit_version,
    "com.squareup.retrofit2:converter-gson:%s" % retrofit_version,
    'com.squareup.okhttp3:logging-interceptor:4.2.1',
    "com.google.dagger:dagger:%s" % dagger_version,
    "com.google.dagger:dagger-android:%s" % dagger_version,
    'joda-time:joda-time:2.10.4'
    
    # kapt "com.google.dagger:dagger-compiler:$dagger_version"
    # kapt "com.google.dagger:dagger-android-processor:$dagger_version"

    # implementation 

    # // Test dependencies
    # testImplementation "junit:junit:4.12"
    # androidTestImplementation "androidx.test.ext:junit:1.1.1"
    # androidTestImplementation "androidx.test.espresso:espresso-core:3.2.0"

    ],
    repositories = [
        # DAGGER_ANDROID_REPOSITORIES,
        "https://maven.google.com",
        "https://repo1.maven.org/maven2/"
    ],
    fetch_sources = True,
)

# Top-level WORKSPACE file


