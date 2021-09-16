package com.shortcut.explorer.presentation._base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shortcut.explorer.presentation.util.Inflate
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class  BaseFragment<E:BaseViewModel>(private val inflate: Inflate<View>) : Fragment() {

    @Inject
    protected lateinit var viewModel: E



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflate.invoke(inflater,container,false).apply {

        }
    }

}