package com.mashup.presentation.common.extension

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

internal fun View.navigate(actionId: Int) {
    Navigation.findNavController(this).navigate(actionId)
}

internal fun View.navigateWithBundle(actionId: Int, args: Bundle) {
    Navigation.findNavController(this).navigate(actionId, args)
}

internal fun View.navigateWithArgs(navDirections: NavDirections) {
    Navigation.findNavController(this).navigate(navDirections)
}

internal fun Fragment.navigate(actionId: Int) {
    findNavController().navigate(actionId)
}

internal fun Fragment.navigateWithBundle(actionId: Int, args: Bundle) {
    findNavController().navigate(actionId, args)
}

internal fun Fragment.navigateWithArgs(navDirections: NavDirections) {
    findNavController().navigate(navDirections)
}

internal fun Fragment.navigateUp() {
    findNavController().navigateUp()
}

internal fun Fragment.popBackStack() {
    findNavController().popBackStack()
}