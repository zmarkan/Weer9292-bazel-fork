package nl.tcilegnar.weer9292.ui

import androidx.fragment.app.Fragment

abstract class BaseBottomNavigationFragment : Fragment() {
    protected fun setActionbarTitle(title: String) {
        (context as MainActivity).setActionBarTitle(title)
    }
}
