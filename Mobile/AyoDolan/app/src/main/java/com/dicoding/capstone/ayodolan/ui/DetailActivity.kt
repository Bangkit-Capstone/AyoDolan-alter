package com.dicoding.capstone.ayodolan.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.MainViewModel
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.dicoding.capstone.ayodolan.core.data.ui.ReviewAdapter
import com.dicoding.capstone.ayodolan.core.data.ui.VacationAdapter
import com.dicoding.capstone.ayodolan.databinding.ActivityDetailBinding
import com.dicoding.capstone.ayodolan.ui.list.ListActivity

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_KEY = "extra_key"
        const val POSITION = "position"
    }
    private lateinit var binding : ActivityDetailBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<VacationEntity>(EXTRA_KEY) as VacationEntity
        binding.toolBar.title = data.title
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val vacationAdapter = ReviewAdapter()

        showDetail(data)
        val extras = intent.extras
        if (extras != null){
            val position = extras.getInt(POSITION,0)
            mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
            mainViewModel.getPantai().observe(this,{
                vacationAdapter.setReview(it[position].review)
            })
        }



        with(binding){
            content.rvReview.layoutManager = LinearLayoutManager(this@DetailActivity)
            content.rvReview.setHasFixedSize(true)
            content.rvReview.adapter = vacationAdapter
            content.rvReview.addItemDecoration(DividerItemDecoration(this@DetailActivity,LinearLayoutManager.VERTICAL))
        }

    }

    fun showDetail(data :VacationEntity){
        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${data.image}")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imageDetail)

        with(binding){
            content.starRating.rating = data.rating.toFloat()
            content.rating.text = data.rating.toString()

            share.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, data.title)
                    putExtra(Intent.EXTRA_TEXT,"Rating:${data.rating} ")
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