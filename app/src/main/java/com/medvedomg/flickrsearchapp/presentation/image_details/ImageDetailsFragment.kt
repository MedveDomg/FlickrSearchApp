package com.medvedomg.flickrsearchapp.presentation.image_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.medvedomg.flickrsearchapp.databinding.FragmentImageDetailsBinding
import com.medvedomg.flickrsearchapp.presentation.spots.ImageModel

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
        val spotModel = arguments?.getSerializable(IMAGE_MODEL_KEY) as ImageModel
        with(view) {
//            Glide.with(view.context)
//                .load(spotModel.imageUrl)
//                .into(findViewById(R.id.iv))
//            findViewById<TextView>(R.id.tvLocation).text = spotModel.address
//            findViewById<TextView>(R.id.tvDistance).text = spotModel.distance
//            findViewById<TextView>(R.id.tvDescription).text = spotModel.description
//            with(findViewById<Button>(R.id.btn)) {
//                text = "Book for \$${spotModel.getReadablePrice()}"
//                setOnClickListener {
//                    // todo: implement
//                }
//            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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