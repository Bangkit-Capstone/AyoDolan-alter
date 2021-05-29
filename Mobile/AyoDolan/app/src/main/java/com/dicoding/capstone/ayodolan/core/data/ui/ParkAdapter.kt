package com.dicoding.capstone.ayodolan.core.data.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.ParkEntity
import com.dicoding.capstone.ayodolan.databinding.ItemListBinding

class ParkAdapter : RecyclerView.Adapter<ParkAdapter.ParkViewHolder>() {

    private var listPark = ArrayList<ParkEntity>()

    fun setPark(park : List<ParkEntity>?){
        if (park == null) return this.listPark.clear()
        this.listPark.clear()
        this.listPark.addAll(park)
        notifyDataSetChanged()
    }
    class ParkViewHolder (private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(park: ParkEntity){
            with(binding){
                itemTitle.text = park.title
                itemLocation.text = park.location

                Glide.with(itemView.context)
                    .load(park.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(itemImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ParkViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val park = listPark[position]
        holder.bind(park)
    }

    override fun getItemCount(): Int {
        return listPark.size
    }
}