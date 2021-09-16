package com.shortcut.explorer.presentation.recent

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.databinding.FragmentRecentBinding
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.util.TopSpacingItemDecoration

class RecentFragment : BaseFragment<FragmentRecentBinding,RecentViewModel>(FragmentRecentBinding::inflate),
    SwipeRefreshLayout.OnRefreshListener, ComicsListAdapter.Interaction {

    private var recyclerAdapter: ComicsListAdapter? = null // can leak memory so need to null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener(this)

        initRecyclerView()
        subscribeObservers()

    }

    private fun initRecyclerView(){
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            removeItemDecoration(topSpacingDecorator) // does nothing if not applied already
            addItemDecoration(topSpacingDecorator)

            recyclerAdapter = ComicsListAdapter(this@RecentFragment)
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
        viewModel.recentComics.observe(viewLifecycleOwner, { comicsList ->
            if (comicsList==null)
                getRecentComics()

            else
                recyclerAdapter?.submitList(comicList = comicsList)
        })
    }

    private fun getRecentComics() {
        lifecycleScope.launchWhenResumed {
            viewModel.getRecentComics { messsage, id ->
                // display error message.
            }
        }
    }

    override fun onRefresh() {
        binding.swipeRefresh.isRefreshing = false

        TODO("Not yet implemented")
    }

    override fun onItemSelected(position: Int, item: Comic) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter = null
    }

}