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
import com.shortcut.explorer.business.domain.model.DetailedComic
import com.shortcut.explorer.business.domain.model.SearchResult
import com.shortcut.explorer.databinding.FragmentComicDetailsBinding
import com.shortcut.explorer.databinding.FragmentSearchBinding
import com.shortcut.explorer.presentation.SharedViewModel
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.util.TopSpacingItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, SharedViewModel>(FragmentComicDetailsBinding::inflate){

    private val comicObj = MutableLiveData<DetailedComic>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comicObj.observe(viewLifecycleOwner){
            binding.comic = it
        }

        val comic = arguments?.getSerializable(Constants.SERIALIZABLE_COMIC_OBJECT_NAME) as DetailedComic?
        if (comic==null) {
            // Throw error
            findNavController().navigateUp()
        }else
            comicObj.value = comic

//        viewModel.retrieveComicExplanation(comic.pId)
    }

    private fun subscribeObservers(){


    }
}