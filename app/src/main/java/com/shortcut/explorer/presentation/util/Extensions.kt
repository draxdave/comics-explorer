package com.shortcut.explorer.presentation.util

import android.view.View
import androidx.fragment.app.Fragment
import com.shortcut.explorer.R
import ir.drax.modal.Modal
import ir.drax.modal.ModalBuilder
import ir.drax.modal.model.MoButton

/**
 * Display message as SnackBar/[Modal] from the top inside fragments
 *
 * @param message : [Int] message resource id.
 * @param message : [String] message text.
 * @param actionText : [String] text on the action button.
 * @param callback : [View.OnClickListener] do after it's clicked.
 */
fun Fragment.message(
    message: Int,
    actionText: String = "",
    callback: View.OnClickListener? = null
) {
    message(getString(message), actionText, callback)
}

fun Fragment.message(
    message: String,
    actionText: String = "",
    callback: View.OnClickListener? = null
): ModalBuilder {
    val modal = Modal.builder(requireActivity()).apply {
        setMessage(message)
        icon = R.drawable.ic_baseline_error_outline_24
        direction = Modal.Direction.Top
        if (actionText.isNotEmpty())
            setCallback(MoButton(actionText, 0) {
                callback?.onClick(it)
                true
            })

    }.build()
    modal.show()
    return modal
}