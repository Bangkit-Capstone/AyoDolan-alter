package com.dicoding.capstone.ayodolan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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

        Log.e("Detail", "${data.title}")

        binding.toolBar.title = data?.title

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*val bitmap = BitmapFactory.decodeResource(resources, R.drawable.beach)
        Palette.from(bitmap).generate{palete ->
        if (palete != null){
            binding.collapsLayout.setContentScrimColor(palete.getMutedColor(R.attr.colorPrimary))
        }
        }*/

        Glide.with(this)
            .load(data?.image)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh).error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imageDetail)

        binding.content.starRating.rating = data?.rating.toFloat()
        binding.content.rating.text = data?.rating
        binding.content.textLocation.text = data.location
        binding.content.textDesc.text = data.description
    }

    fun viewData(vacation :VacationEntity){

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