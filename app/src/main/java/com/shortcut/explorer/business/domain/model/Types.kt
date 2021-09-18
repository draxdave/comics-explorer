package com.shortcut.explorer.business.domain.model


/**
 * @see com.shortcut.explorer.presentation.util.FlowObserver
 * Triggered if a new emit is received and an update is observed.
 */
typealias OnChange<T> =(Resource<T>)->Unit