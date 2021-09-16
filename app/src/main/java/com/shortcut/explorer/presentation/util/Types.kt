package com.shortcut.explorer.presentation.util

import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * It's used to inflate fragments from [].
 *
 * @param LayoutInflater : Layout inflater to be used as inflater.
 * @param ViewGroup : container view to be inflated to.
 */
typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T