package com.dicoding.capstone.ayodolan.core.data.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.dicoding.capstone.ayodolan.databinding.ItemListBinding
import com.dicoding.capstone.ayodolan.ui.DetailActivity

class VacationAdapter :RecyclerView.Adapter<VacationAdapter.BeachViewHolder>() {

  private var listVacation = ArrayList<VacationEntity>()

    fun setVacation(vacations : List<VacationEntity>?){
        if (vacations == null) return this.listVacation.clear()
        this.listVacation.clear()
        this.listVacation.addAll(vacations)
        notifyDataSetChanged()
    }

   class BeachViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vacation : VacationEntity){
            with(binding){
                itemTitle.text = vacation.title
                textRate.text = vacation.rating.toString()
                starRate.rating = vacation.rating.toFloat()

                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${vacation.image}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
                    .into(itemImage)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_KEY,vacation)
                    intent.putExtra(DetailActivity.POSITION,adapterPosition)
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
        val beach = listVacation[position]
        holder.bind(beach)
    }

    override fun getItemCount(): Int {
        return listVacation.size
    }

}