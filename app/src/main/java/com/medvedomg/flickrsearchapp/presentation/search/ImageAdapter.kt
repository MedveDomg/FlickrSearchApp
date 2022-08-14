package com.medvedomg.flickrsearchapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.medvedomg.flickrsearchapp.R
import com.medvedomg.flickrsearchapp.databinding.ImageViewholderBinding

class ImageAdapter(val onClickCallback: (ImageModel) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var itemsList: MutableList<ImageModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageViewholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size

    fun setData(list: List<ImageModel>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(val binding: ImageViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageModel: ImageModel) {
            with(binding) {
                iv.load(imageModel.link) {
                    error(R.drawable.ic_baseline_error_24)
                }
                tvTitle.text = imageModel.title
                root.setOnClickListener {
                    onClickCallback(imageModel)
                }
            }
        }
    }
}