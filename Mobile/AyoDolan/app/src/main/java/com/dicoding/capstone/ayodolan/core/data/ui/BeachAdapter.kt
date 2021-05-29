package com.dicoding.capstone.ayodolan.core.data.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.BeachEntity
import com.dicoding.capstone.ayodolan.databinding.ItemListBinding
import com.dicoding.capstone.ayodolan.ui.DetailActivity

class BeachAdapter :RecyclerView.Adapter<BeachAdapter.BeachViewHolder>() {

  private var listBeach = ArrayList<BeachEntity>()

    fun setBeach(beach : List<BeachEntity>?){
        if (beach == null) return this.listBeach.clear()
        this.listBeach.clear()
        this.listBeach.addAll(beach)
        notifyDataSetChanged()
    }

   class BeachViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beach : BeachEntity){
            with(binding){
                itemTitle.text = beach.title
                itemLocation.text = beach.location
                textRate.text = beach.rating
                starRate.rating = beach.rating.toFloat()

                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${beach.image}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(itemImage)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_KEY,beach)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeachViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BeachViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: BeachViewHolder, position: Int) {
        val beach = listBeach[position]
        holder.bind(beach)
    }

    override fun getItemCount(): Int {
        return listBeach.size
    }

}