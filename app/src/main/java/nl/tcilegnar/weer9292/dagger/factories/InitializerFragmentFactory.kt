package nl.tcilegnar.weer9292.dagger.factories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlin.reflect.KClass

/**
 * A [FragmentFactory] that allows you to add lambda initializers for handling
 * particular classes, while using the default behavior for any other classes.
 *
 * ```
 * supportFragmentManager.fragmentFactory = InitializerFragmentFactory().apply {
 *   addInitializer { ProfileFragment(viewModelFactory) }
 * }
 * ```
 */
class InitializerFragmentFactory : FragmentFactory() {
    private val initializers = mutableMapOf<String, () -> Fragment>()

    operator fun get(clazz: KClass<Fragment>): (() -> Fragment)? {
        return initializers[clazz.java.name]
    }

    operator fun <F : Fragment> set(clazz: KClass<F>, initializer: () -> F) {
        initializers[clazz.java.name] = initializer
    }

    override fun instantiate(classLoader: ClassLoader, className: String) =
        initializers[className]?.invoke()
            ?: super.instantiate(classLoader, className)
}

inline fun <reified F : Fragment> InitializerFragmentFactory.addInitializer(
    noinline initializer: () -> F
) {
    this[F::class] = initializer
}
