package nl.tcilegnar.weer9292.ui.radar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RadarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Weather radar feature coming soon!"
    }
    val text: LiveData<String> = _text
}
