package com.eugenebrusov.news.util

import android.arch.paging.PagedList
import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.eugenebrusov.news.GlideApp
import com.eugenebrusov.news.data.model.Listing
import com.eugenebrusov.news.data.model.NewsItem
import com.eugenebrusov.news.data.model.NewsSection
import com.eugenebrusov.news.data.model.Resource
import com.eugenebrusov.news.newslist.NewsListActivity
import com.eugenebrusov.news.newslist.NewsListPagedAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Eugene Brusov on 1/7/18.
 */
object Bindings {

    @BindingAdapter("app:results")
    @JvmStatic fun setItems(recyclerView: RecyclerView, results: Resource<Listing<NewsItem>>?) {
        with(recyclerView.adapter as NewsListPagedAdapter) {
            this.results = results
        }
    }

    @BindingAdapter("app:sectionsResource")
    @JvmStatic fun setSectionsResource(viewPager: ViewPager, sectionsResource: Resource<List<NewsSection>>?) {
        with(viewPager.adapter as NewsListActivity.ViewPagerAdapter) {
            this.sectionsResource = sectionsResource
        }
    }

    @BindingAdapter("app:thumbnail")
    @JvmStatic fun setThumbnail(imageView: ImageView, thumbnail: String?) {
        GlideApp.with(imageView.context).load(thumbnail).into(imageView)
    }

    @BindingAdapter("app:byline")
    @JvmStatic fun setByline(imageView: ImageView, bylineImageUrl: String?) {
        GlideApp.with(imageView.context)
                .load(bylineImageUrl)
                .apply(RequestOptions().circleCrop())
                .into(imageView)
    }

    @BindingAdapter("app:webPublicationDate")
    @JvmStatic fun setWebPublicationDate(textView: TextView, webPublicationDate: Long?) {
        textView.text = ""
        if (webPublicationDate != null) {
            val formattedDate: String? =
                    try {
                        SimpleDateFormat("MMM d, yyyy", Locale.US).format(Date(webPublicationDate))
                    } catch (e: Exception) { null }
            textView.text = formattedDate
        }
    }

    @BindingAdapter("app:body")
    @JvmStatic fun setBody(textView: TextView, bodyText: String?) {
        textView.text = bodyText?.replace(". ", ".\n\n")
    }

    @BindingAdapter("app:refreshEnabled")
    @JvmStatic fun setRefreshEnabled(refreshLayout: SwipeRefreshLayout, enabled: Boolean?) {
        refreshLayout.isEnabled = (enabled == true)
    }

    @BindingAdapter("app:layoutHeight")
    @JvmStatic fun setLayoutHeight(layout: ViewGroup, height: Int?) {
        val layoutParams = layout.layoutParams
        layoutParams.height = height ?: 0
        layout.layoutParams = layoutParams
    }
}