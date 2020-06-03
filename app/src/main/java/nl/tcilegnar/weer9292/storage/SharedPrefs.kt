package nl.tcilegnar.weer9292.storage

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import nl.tcilegnar.weer9292.storage.SharedPrefs.Companion.APP_PREFS

/**
 * Wrapper for preferences to simply save & load each value type.
 *
 * When extending this class you need to define a filename, to easily organize your prefs in separate directories.
 * When using [APP_PREFS] as filename, the preferences will be saved at the root ([PreferenceManager.getDefaultSharedPreferences])
 *
 * You can delete all preferences from a single directory using [deletePreferences]
 */
@Suppress("SameParameterValue", "unused")
abstract class SharedPrefs(context: Context) : ContextWrapper(context) {
    /** Expose applicationContext as default context. It's always safe to use this if you're not sure which to use! */
    protected val context: Context = applicationContext

    companion object {
        const val APP_PREFS = "ROOT_APPLICATION_PREFS"
    }

    protected abstract fun prefsFileName(): String

    private val isAppPrefs: Boolean
        get() = prefsFileName() == APP_PREFS

    protected val prefs: SharedPreferences
        get() {
            return if (isAppPrefs) {
                PreferenceManager.getDefaultSharedPreferences(context)
            } else {
                context.getSharedPreferences(prefsFileName(), Context.MODE_PRIVATE)
            }
        }

    private fun prefsEdit() = prefs.edit()

    // Booleans
    protected fun save(key: String, value: Boolean) {
        prefsEdit().putBoolean(key, value).apply()
    }

    @JvmOverloads
    protected fun loadBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    protected fun loadBoolean(key: String, defaultValue: String): Boolean {
        return loadBoolean(key, java.lang.Boolean.valueOf(defaultValue))
    }

    // Strings
    protected fun save(key: String, value: String) {
        prefsEdit().putString(key, value).apply()
    }

    @JvmOverloads
    protected fun loadString(key: String, defaultValue: String = ""): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    @JvmOverloads
    protected fun loadStringLiveData(key: String, defaultValue: String = ""): SharedPreferenceLiveData<String> {
        return prefs.stringLiveData(key, defaultValue)
    }

    // Integers
    protected fun save(key: String, value: Int) {
        prefsEdit().putInt(key, value).apply()
    }

    @JvmOverloads
    protected fun loadInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    protected fun loadInt(key: String, defaultValue: String): Int {
        return loadInt(key, Integer.valueOf(defaultValue))
    }

    // Floats
    protected fun save(key: String, value: Float) {
        prefsEdit().putFloat(key, value).apply()
    }

    @JvmOverloads
    protected fun loadFloat(key: String, defaultValue: Float = 0f): Float {
        return prefs.getFloat(key, defaultValue)
    }

    protected fun loadFloat(key: String, defaultValue: String): Float {
        return loadFloat(key, java.lang.Float.valueOf(defaultValue))
    }

    // Longs
    protected fun save(key: String, value: Long) {
        prefsEdit().putLong(key, value).apply()
    }

    @JvmOverloads
    protected fun loadLong(key: String, defaultValue: Long = 0): Long {
        return prefs.getLong(key, defaultValue)
    }

    protected fun loadLong(key: String, defaultValue: String): Long {
        return loadLong(key, java.lang.Long.valueOf(defaultValue))
    }

    // StringSets
    protected fun save(key: String, value: Set<String>) {
        prefsEdit().putStringSet(key, value).apply()
    }

    @JvmOverloads
    protected fun loadStringSet(key: String, defaultValue: Set<String>? = null): Set<String>? {
        return prefs.getStringSet(key, defaultValue)
    }

    fun deletePreferences() {
        prefs.edit().clear().apply()
    }
}
