package com.dicoding.capstone.ayodolan.core.data.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.ReviewEntity
import com.dicoding.capstone.ayodolan.databinding.ItemListBinding
import com.dicoding.capstone.ayodolan.databinding.ItemReviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ParkViewHolder>() {

    private var listPark = ArrayList<ReviewEntity>()

    fun setPark(review : List<ReviewEntity>?){
        if (review == null) return this.listPark.clear()
        this.listPark.clear()
        this.listPark.addAll(review)
        notifyDataSetChanged()
    }
    class ParkViewHolder (private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: ReviewEntity){
            with(binding){
                textUsername.text = review.username
                textStar.text = review.rating
                starRateReview.rating = review.rating.toFloat()
                textReview.text = review.review
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val itemReviewBinding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ParkViewHolder(itemReviewBinding)
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val park = listPark[position]
        holder.bind(park)
    }

    override fun getItemCount(): Int {
        return listPark.size
    }
}