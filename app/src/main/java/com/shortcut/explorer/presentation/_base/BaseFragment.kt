package com.shortcut.explorer.presentation._base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.shortcut.explorer.BR
import com.shortcut.explorer.presentation.util.Inflate
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class  BaseFragment<T: ViewDataBinding, E:BaseViewModel>(private val inflate: Inflate<T>) : Fragment() {
    val TAG: String = "AppDebug"


    @Inject
    protected lateinit var viewModel: E

    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)

        return inflate.invoke(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner

            /**
             * Viewmodel is set to the fragment binding here via Variable set.
             */
            setVariable(BR.model,viewModel)
            _binding = this
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}