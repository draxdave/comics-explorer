package com.shortcut.explorer.presentation.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.shortcut.explorer.R
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.databinding.ComicListItemBinding

class ComicsListAdapter(
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val requestOptions = RequestOptions
        .placeholderOf(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_baseline_error_outline_24)

    private val TAG: String = "AppDebug"

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comic>() {

        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.num == newItem.num
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }

    }
    private val differ =
        AsyncListDiffer(
            ComicRecyclerChangeCallback(this),
            AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
        )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ComicViewHolder(
            ComicListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            requestOptions = requestOptions,
            interaction = interaction,
        )
    }

    internal inner class ComicRecyclerChangeCallback(
        private val adapter: ComicsListAdapter
    ) : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ComicViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(comicList: List<Comic>?, ){
        val newList = comicList?.toMutableList()
        differ.submitList(newList)
    }

    class ComicViewHolder
    constructor(
        private val binding: ComicListItemBinding,
        private val requestOptions: RequestOptions,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comic) {
            binding.root.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            Glide.with(binding.root)
                .setDefaultRequestOptions(requestOptions)
                .load(item.imgUrl)
                .transition(withCrossFade())
                .into(binding.cover)
            binding.titleTv.text = item.title
            binding.snippetTv.text = item.description
        }
    }

    interface Interaction {

        fun onItemSelected(position: Int, item: Comic)

    }
}
