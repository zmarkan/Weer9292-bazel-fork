package nl.tcilegnar.weer9292.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.tcilegnar.weer9292.R
import nl.tcilegnar.weer9292.model.TemperatureUnit
import nl.tcilegnar.weer9292.storage.TemperaturePrefs

class MainActivity : AppCompatActivity() {
    private lateinit var temperaturePrefs: TemperaturePrefs
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperaturePrefs = TemperaturePrefs(this)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_forecast,
                R.id.navigation_radar
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        this.menu = menu
        setTemperatureUnitIcon(temperaturePrefs.getTemperatureUnit())
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val newUnit = temperaturePrefs.toggleTemperatureUnit()
            setTemperatureUnitIcon(newUnit)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }

    private fun setTemperatureUnitIcon(newIcon: TemperatureUnit) {
        menu.getItem(0).icon = newIcon.getMenuIcon(this)
    }
}
