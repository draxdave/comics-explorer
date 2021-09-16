package com.shortcut.explorer.presentation.util

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.gms.maps.model.LatLng
import ir.drax.foursqare.network.model.ServerResponse


/**
 * It's used to inflate fragments from [].
 *
 * @param LayoutInflater : Layout inflater to be used as inflater.
 * @param ViewGroup : container view to be inflated to.
 */
typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T