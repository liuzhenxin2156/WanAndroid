package com.example.myapplication.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modle.ArticleData
import com.example.myapplication.modle.DataX
import com.win.lib_base.service.webview.warp.WebViewWarpService
import com.win.lib_image_loader.app.ImageLoaderManager
import kotlinx.android.synthetic.main.fragment_about_tab.view.*
import kotlinx.android.synthetic.main.item_home_list.view.*
import kotlinx.android.synthetic.main.item_home_list.view.tv_title

class AboutTabAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    private var dataX: List<DataX> = ArrayList<DataX>()
    private val mContext = context

    fun setData(dataList: List<DataX>) {
        this.dataX = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.fragment_about_tab, parent, false)
        return MyViewHolder(itemView,mContext)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataX[position])
    }

    override fun getItemCount(): Int = dataX.size ?: 0
}

class MyViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
    private var mContext = context
    fun bind(article: DataX) {

        itemView.title_tv.text = article.title
        itemView.desc.text = article.desc
        itemView.author.text = article.author
        itemView.date.text = article.niceDate
        ImageLoaderManager.instance
                .displayImageForView(itemView.pic, article.envelopePic)
        itemView.setOnClickListener {
            WebViewWarpService.instance.start(mContext, article.title, article.link)
        }
    }
}