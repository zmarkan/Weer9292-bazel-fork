package nl.tcilegnar.weer9292.dagger

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import nl.tcilegnar.weer9292.MyApplication
import nl.tcilegnar.weer9292.dagger.modules.ActivityBindings
import nl.tcilegnar.weer9292.dagger.modules.ApiModule
import nl.tcilegnar.weer9292.dagger.modules.RepositoriesModule
import nl.tcilegnar.weer9292.dagger.scopes.ApplicationScope

@ApplicationScope
@Component(
    modules = [
        ApplicationBindings::class,
        ActivityBindings::class,
        RepositoriesModule::class,
        ApiModule::class,
        AndroidInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
abstract class ApplicationBindings {
    @Binds
    abstract fun bindsContext(context: Application): Context
}
