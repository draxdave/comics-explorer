package com.shortcut.explorer.presentation.details

import android.R.attr
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shortcut.explorer.business.datasource.network.search.toExplanation
import com.shortcut.explorer.business.domain.Constants
import com.shortcut.explorer.business.domain.model.DetailedComic
import com.shortcut.explorer.business.domain.model.toFavorite
import com.shortcut.explorer.databinding.FragmentComicDetailsBinding
import com.shortcut.explorer.presentation.SharedViewModel
import com.shortcut.explorer.presentation._base.BaseFragment
import com.shortcut.explorer.presentation.util.message
import com.shortcut.explorer.presentation.util.observe
import com.shortcut.explorer.presentation.util.observeOnceNotNull
import kotlinx.coroutines.InternalCoroutinesApi
import android.content.Intent

import android.R.attr.bitmap
import android.content.ClipDescription
import android.graphics.Bitmap
import android.net.Uri

import android.provider.MediaStore




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

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.shareFab.setOnClickListener {
            shareComic()
        }

        binding.faveFab.setOnClickListener{

            viewModel.isCached.observeOnceNotNull(viewLifecycleOwner){ isCached ->
                lifecycleScope.launchWhenResumed {

                    if (isCached)
                        viewModel.removeFavorite(comicObj.value!!.toFavorite())
                    else
                        viewModel.addFavorite(comicObj.value!!.toFavorite())

                }
            }
        }
    }

    private fun shareComic() {
        comicObj.observeOnceNotNull(viewLifecycleOwner){

        }
    }

    private fun share(bitmap: Bitmap, title:String, description: String){
        val bitmapPath: String =
            MediaStore.Images.Media.insertImage(requireActivity().contentResolver, bitmap, title, description)
        val bitmapUri: Uri = Uri.parse(bitmapPath)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey, Check this out:\n$title\n\n$description"
        )

        startActivity(Intent.createChooser(intent, "Share with"))
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
                        message(if (string.isNullOrEmpty()) getString(id) else string)
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

                        message(if (string.isNullOrEmpty()) getString(id) else string)

                    }

                ) {
                    it.status.onSuccess {

                        comicObj.value = comicObj.value?.apply {
                            imgUrl = it.data?.img
                        }

                    }
                }

            viewModel.setIsCached(comic.num)
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