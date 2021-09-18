package com.shortcut.explorer.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shortcut.explorer.business.domain.Constants
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.SearchResult
import com.shortcut.explorer.databinding.FragmentComicDetailsBinding
import com.shortcut.explorer.databinding.FragmentSearchBinding
import com.shortcut.explorer.presentation.SharedViewModel
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.util.TopSpacingItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, SharedViewModel>(FragmentComicDetailsBinding::inflate){

    private val comicObj = MutableLiveData<Comic>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}