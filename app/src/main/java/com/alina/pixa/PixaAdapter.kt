package com.alina.pixa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.alina.pixa.databinding.ItemImageBinding

class PixaAdapter(val list: ArrayList<ImageModel>) : Adapter<PixaAdapter.PixaViewHolder>() {
    class PixaViewHolder(var binding: ItemImageBinding) : ViewHolder(binding.root) {

        fun bind(imageModel: ImageModel) {
            binding.imgImage.load(imageModel.largeImageURL)
        }

    }

    fun addData(list: List<ImageModel>) {
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.bind(list[position])
    }


}



