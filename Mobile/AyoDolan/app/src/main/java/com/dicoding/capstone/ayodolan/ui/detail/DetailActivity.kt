package com.dicoding.capstone.ayodolan.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.dicoding.capstone.ayodolan.core.data.ui.ReviewAdapter
import com.dicoding.capstone.ayodolan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_KEY = "extra_key"
        const val POSITION = "position"
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

        val vacationAdapter = ReviewAdapter()

        showDetail(data)

        vacationAdapter.setReview(data.review)

        with(binding){
            content.rvReview.layoutManager = LinearLayoutManager(this@DetailActivity)
            content.rvReview.setHasFixedSize(true)
            content.rvReview.adapter = vacationAdapter
            content.rvReview.addItemDecoration(DividerItemDecoration(this@DetailActivity,LinearLayoutManager.VERTICAL))
        }

    }

    private fun showDetail(data :VacationEntity){
        Glide.with(this)
            .load(data.image)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imageDetail)

        with(binding){
            content.starRating.rating = (data.rating.toFloat() * 5) / 100
            content.rating.text = data.rating.toFloat().toString()

            share.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, data.title)
                    putExtra(Intent.EXTRA_TEXT,"${data.title}\nRating:${data.rating} ")
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