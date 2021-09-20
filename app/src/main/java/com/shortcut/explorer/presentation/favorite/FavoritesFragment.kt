package com.shortcut.explorer.presentation.favorite

import android.os.Bundle
import android.text.Spanned
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shortcut.explorer.business.datasource.db.favorites.toComic
import com.shortcut.explorer.business.domain.Constants
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.DetailedComic
import com.shortcut.explorer.business.domain.model.toDetailedComic
import com.shortcut.explorer.databinding.FragmentFavoritesBinding
import com.shortcut.explorer.databinding.FragmentRecentBinding
import com.shortcut.explorer.presentation.SharedViewModel
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.recent.ComicsListAdapter
import com.shortcut.explorer.presentation.util.FlowObserver
import com.shortcut.explorer.presentation.util.TopSpacingItemDecoration
import com.shortcut.explorer.presentation.util.message
import kotlinx.coroutines.flow.collect

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, SharedViewModel>(FragmentFavoritesBinding::inflate)
    , ComicsListAdapter.Interaction {

    private var recyclerAdapter: ComicsListAdapter? = null // can leak memory so need to null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView(){
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            removeItemDecoration(topSpacingDecorator) // does nothing if not applied already
            addItemDecoration(topSpacingDecorator)

            recyclerAdapter = ComicsListAdapter(this@FavoritesFragment)
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
                        viewModel.loadMore()
                    }
                }
            })
            adapter = recyclerAdapter
        }
    }

    private fun subscribeObservers(){
        lifecycleScope.launchWhenResumed {
            viewModel.favoriteComics.collect {

                recyclerAdapter?.submitList(
                    comicList = it
                        .map { favoriteEntity ->
                            favoriteEntity.toComic()
                        }
                )
            }
        }
    }

    override fun onItemSelected(position: Int, item: Comic) = gotoDetails(item)

    private fun gotoDetails(item: Comic) {
        findNavController().navigate(
            FavoritesFragmentDirections.toDetailsPage(
                item.toDetailedComic()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter = null
    }

}