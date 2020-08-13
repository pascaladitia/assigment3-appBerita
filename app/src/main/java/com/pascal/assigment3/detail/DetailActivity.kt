package com.pascal.assigment3.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.pascal.assigment3.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")
        val author = intent.getStringExtra("author")
        val image = intent.getStringExtra("image")

        detail_title.text = title
        detail_desc.text = desc
        detail_author.text = author

        Glide.with(this)
            .load(image)
            .into(detail_image)
    }
}