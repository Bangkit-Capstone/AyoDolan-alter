package com.dicoding.capstone.ayodolan.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.dicoding.capstone.ayodolan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_KEY = "extra_key"
    }
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<VacationEntity>(EXTRA_KEY) as VacationEntity
        binding.toolBar.title = data.title
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showDetail(data)

    }

    fun showDetail(data :VacationEntity){
        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${data.image}")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imageDetail)

        with(binding){
            content.starRating.rating = data.rating.toFloat()
            content.rating.text = data.rating
            content.textLocation.text = data.location
            content.textDesc.text = data.description

            share.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, data.title)
                    putExtra(Intent.EXTRA_TEXT,"Rating:${data.rating} \n ${data.description}")
                    type = "text/plain"
                }
                startActivity(sendIntent)
            }
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}