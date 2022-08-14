package com.medvedomg.flickrsearchapp.presentation.image_details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.medvedomg.flickrsearchapp.databinding.FragmentImageDetailsBinding
import com.medvedomg.flickrsearchapp.presentation.search.ImageModel

class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = arguments?.getSerializable(IMAGE_MODEL_KEY) as ImageModel
        with(binding) {
            iv.load(model.link)
            tvTitle.text = model.title
            tvDescription.text = Html.fromHtml(model.description)
            tvSize.text = model.getSize()
            tvAuthor.text = "Author: ${model.author}"
        }
    }

    companion object {

        private const val IMAGE_MODEL_KEY = "IMAGE_MODEL_KEY"

        fun newInstance(imageModel: ImageModel): ImageDetailsFragment {
            val fragment = ImageDetailsFragment()
            fragment.arguments = bundleOf(IMAGE_MODEL_KEY to imageModel)
            return fragment
        }
    }
}