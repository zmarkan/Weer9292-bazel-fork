package nl.tcilegnar.weer9292.dagger.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nl.tcilegnar.weer9292.dagger.scopes.ActivityScope
import nl.tcilegnar.weer9292.ui.MainActivity

@Module
abstract class ActivityContributor {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}
