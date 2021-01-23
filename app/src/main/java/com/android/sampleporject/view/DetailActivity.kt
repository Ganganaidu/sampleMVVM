package com.android.sampleporject.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.MenuItem
import com.android.sampleporject.R
import com.android.sampleporject.base.view.BaseActivity
import com.android.sampleporject.databinding.ActivityDetailBinding
import com.android.sampleporject.model.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpData() {
        val item: Item? = intent.extras?.getParcelable("item")
        if (!item?.title.isNullOrEmpty()) {
            supportActionBar?.title = item?.title
        }
        item?.author?.let {
            binding.author.text = getString(R.string.author).replace("^", it)
        }
        binding.desc.text = fromHtml(item?.description)
        binding.desc.movementMethod = LinkMovementMethod.getInstance()
        //Linkify.addLinks(binding.desc, Linkify.WEB_URLS)

        val requestOption = RequestOptions()
            .placeholder(R.drawable.ic_image_loading)

        Glide.with(this)
            .load(item?.media?.mediaLink)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOption)
            .into(binding.image)
    }

    private fun fromHtml(source: String?): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }
}