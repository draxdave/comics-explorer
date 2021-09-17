package com.shortcut.explorer.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.databinding.FragmentRecentBinding
import com.shortcut.explorer.databinding.FragmentSearchBinding
import com.shortcut.explorer.presentation.SharedViewModel
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.recent.ComicsListAdapter
import com.shortcut.explorer.presentation.util.TopSpacingItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class SearchFragment : BaseFragment<FragmentSearchBinding, SharedViewModel>(FragmentSearchBinding::inflate),
    ComicsListAdapter.Interaction {

    private var recyclerAdapter: ComicsListAdapter? = null // can leak memory so need to null
    private var searchJob: Job?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initFilterInput()
        subscribeObservers()

    }

    private fun initFilterInput() {
        binding.inputFilter.doAfterTextChanged { editable ->
            editable?.toString()?.takeIf { it.length > 1 }?.let {
                searchComicsFor(it)
            }
        }
    }

    private fun initRecyclerView(){
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            removeItemDecoration(topSpacingDecorator) // does nothing if not applied already
            addItemDecoration(topSpacingDecorator)

            recyclerAdapter = ComicsListAdapter(this@SearchFragment)
            addOnScrollListener(object: RecyclerView.OnScrollListener(){

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()

                    if (
                        lastPosition == recyclerAdapter?.itemCount?.minus(1)
                        && viewModel.isLoading.value == false
                    ) {
                        Log.d(TAG, "attempting to load next page...")
                        viewModel.loadMoreSearchResults()
                    }
                }
            })
            adapter = recyclerAdapter
        }
    }

    private fun subscribeObservers(){
        viewModel.searchResult.observe(viewLifecycleOwner, { comicsList ->
            recyclerAdapter?.submitList(comicList = comicsList)
        })
    }

    private fun searchComicsFor(searchPhrase:String) {
        searchJob?.cancel()

        searchJob = lifecycleScope.launchWhenResumed {
            // Cancellability
            delay(500L)

            viewModel.searchComics(searchPhrase) { messsage, id ->
                // display error message.
            }
        }
    }

    override fun onItemSelected(position: Int, item: Comic) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter = null
    }

}