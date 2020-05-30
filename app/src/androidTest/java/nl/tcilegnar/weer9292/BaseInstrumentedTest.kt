package nl.tcilegnar.weer9292

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {
    protected fun getContext(): Context = InstrumentationRegistry.getInstrumentation().targetContext
}
