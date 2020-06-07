package nl.tcilegnar.weer9292

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import nl.tcilegnar.weer9292.dagger.DaggerMyAppComponent
import javax.inject.Inject

class MyApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerMyAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}
