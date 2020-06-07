package nl.tcilegnar.weer9292.util.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import nl.tcilegnar.weer9292.dagger.factories.ViewModelFactory

/** Extension method to request view models (mostly used in onCreate in Fragments) */
inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(factory: ViewModelFactory<T>, clazz: Class<T>): T {
    return when (this) {
        is FragmentActivity -> ViewModelProvider(this, factory).get(clazz)
        is Fragment -> ViewModelProvider(this, factory).get(clazz)
        else -> throw IllegalAccessError("Viewmodels can only be retrieved from a ViewModelStoreOwner")
    }
}
