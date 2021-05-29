package com.dicoding.capstone.ayodolan.core.data.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.CulturalEntity
import com.dicoding.capstone.ayodolan.databinding.ItemListBinding

class CulturalAdapter : RecyclerView.Adapter<CulturalAdapter.CulturalViewHolder>() {

    private var listCultural = ArrayList<CulturalEntity>()

    fun setCultural(cultural: List<CulturalEntity>?){
        if (cultural == null) return this.listCultural.clear()
        this.listCultural.clear()
        this.listCultural.addAll(cultural)
        notifyDataSetChanged()
    }
    class CulturalViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cultural: CulturalEntity){
            with(binding){
                itemTitle.text = cultural.title
                itemLocation.text = cultural.location

                Glide.with(itemView.context)
                    .load(cultural.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(itemImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CulturalViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CulturalViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: CulturalViewHolder, position: Int) {
        val cultural = listCultural[position]
        holder.bind(cultural)
    }

    override fun getItemCount(): Int {
        return listCultural.size
    }

}