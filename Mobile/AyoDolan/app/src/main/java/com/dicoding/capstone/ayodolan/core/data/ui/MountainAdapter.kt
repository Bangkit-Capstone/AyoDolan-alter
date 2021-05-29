package com.dicoding.capstone.ayodolan.core.data.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.MountainEntity
import com.dicoding.capstone.ayodolan.databinding.ItemListBinding

class MountainAdapter : RecyclerView.Adapter<MountainAdapter.MountainViewHolder>() {

   private var listMountain = ArrayList<MountainEntity>()

    fun setMountain(mountain: List<MountainEntity>?){
        if (mountain == null) return this.listMountain.clear()
        this.listMountain.clear()
        this.listMountain.addAll(mountain)
        notifyDataSetChanged()
    }

    class MountainViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(mountain: MountainEntity) {
            with(binding){
                itemTitle.text = mountain.title
                itemLocation.text = mountain.location

                Glide.with(itemView.context)
                    .load(mountain.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(itemImage)
            }
            /*itemView.setOnClickListener{
                val intent = Intent(itemView.context, Details::class.java)
                intent.putExtra()
            }*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MountainViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MountainViewHolder, position: Int) {
        val mountain = listMountain[position]
        holder.bind(mountain)
    }

    override fun getItemCount(): Int {
        return listMountain.size
    }
}