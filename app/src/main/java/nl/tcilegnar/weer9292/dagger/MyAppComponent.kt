package nl.tcilegnar.weer9292.dagger

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import nl.tcilegnar.weer9292.MyApp
import nl.tcilegnar.weer9292.dagger.modules.ActivityContributor
import nl.tcilegnar.weer9292.dagger.modules.ApiModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityContributor::class,
        ApplicationBindings::class,
        ApiModule::class
    ]
)
interface MyAppComponent : AndroidInjector<MyApp> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MyAppComponent
    }
}

@Module
abstract class ApplicationBindings {
    @Binds
    abstract fun bindsContext(context: Application): Context
}
