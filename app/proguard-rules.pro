# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# When editting this file: make sure not to include unnecessary rules. The less the better!
#
# The following link contains many common Android libraries and ProGuard rules for each of them: https://gist.github.com/jemshit/767ab25a9670eb0083bafa65f8d786bb
# Most libraries have a github with their own rules which can be different, like:
# Retrofit: https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
# The same applies for "You might also need rules for OkHttp and Okio which are dependencies of this library.": https://square.github.io/retrofit/

# However, we don't need most of these rules anymore, because the rules can be applied on the libraries themselves.
# Since recently these rules will also be applied to the depending projects, so we don't need to take it into account!
# If any ProGuard issues arise in the future, these rules can be a quick starting point for analyzing!



# Remove logs on release: https://support.brightcove.com/removing-android-log-messages
# https://medium.com/@elye.project/debug-messages-your-responsible-to-clear-it-before-release-1a0f872d66f
-assumenosideeffects class android.util.Log {
  public static *** v(...);
  public static *** d(...);
  public static *** i(...);
  public static *** w(...);
  public static *** e(...);
  public static *** wtf(...);
}



### AndroidX navigation: https://stackoverflow.com/questions/50378810/proguard-causing-runtime-exception-with-android-navigation-component/50378828#50378828
#-keep class * extends androidx.fragment.app.Fragment{} # AndroidX navigation handles this automatically now?
-keep class nl.tcilegnar.weer9292.model.WeatherDetails



##### Custom exclusions for this project: #####

### Retrofit models for request/response calls should not be obfuscated:
#-keep class nl.tcilegnar.weer9292.network.model.** { *; } # Retrofit handles this automatically now?
