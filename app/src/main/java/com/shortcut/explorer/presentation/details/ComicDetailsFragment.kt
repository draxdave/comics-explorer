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
import com.shortcut.explorer.business.datasource.network.search.toExplanation
import com.shortcut.explorer.business.domain.Constants
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.DetailedComic
import com.shortcut.explorer.business.domain.model.SearchResult
import com.shortcut.explorer.databinding.FragmentComicDetailsBinding
import com.shortcut.explorer.databinding.FragmentSearchBinding
import com.shortcut.explorer.presentation.SharedViewModel
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.util.TopSpacingItemDecoration
import com.shortcut.explorer.presentation.util.observe
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, SharedViewModel>(FragmentComicDetailsBinding::inflate){

    private val comicObj = MutableLiveData<DetailedComic>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comic = arguments?.getSerializable(Constants.SERIALIZABLE_COMIC_OBJECT_NAME) as DetailedComic?
        if (comic==null) {
            // Throw error
            findNavController().navigateUp()

        }else
            subscribeObservers(comic)

    }

    private fun getDetails(comic: DetailedComic) {
        lifecycleScope.launchWhenResumed {

            if(comic.explanation==null){

                val explanationFlow =
                    if (comic.pId==null)
                        viewModel.retrieveComicExplanationByPage("${comic.num}: ${comic.title}")

                    else
                        viewModel.retrieveComicExplanationByPage("${comic.num}: ${comic.title}")

                explanationFlow.observe(

                    { id,string->
                        // Display message
                    }

                ) {
                    it.status.onSuccess {

                        comicObj.value = comicObj.value?.apply {
                            explanation = it.data?.toExplanation()
                        }

                    }
                }

            }

            if (comic.imgUrl == null)

                viewModel.retrieveComic(comic.num).observe(

                    { id,string->

                        // Display message

                    }

                ) {
                    it.status.onSuccess {

                        comicObj.value = comicObj.value?.apply {
                            imgUrl = it.data?.img
                        }

                    }
                }
        }
    }


    private fun subscribeObservers(comic: DetailedComic){
        comicObj.observe(viewLifecycleOwner){
            binding.comic = it
        }

        comicObj.value = comic

        getDetails(comic)
    }
}