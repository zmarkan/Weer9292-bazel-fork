package nl.tcilegnar.weer9292.util.extensions

import android.view.View
import android.view.View.*

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun View.showIf(condition: Boolean) {
    if (condition) show() else hide()
}

fun View.toggleVisibility() {
    showIf(visibility == GONE)
}
