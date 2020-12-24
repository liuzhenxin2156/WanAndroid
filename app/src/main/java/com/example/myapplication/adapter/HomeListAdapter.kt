package com.pzj.wanandroid_kotlin.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modle.ArticleData
import com.win.lib_base.service.webview.warp.WebViewWarpService
import kotlinx.android.synthetic.main.item_home_list.view.*


/**
 * @description: HomeListAdapter
 * @author: pengzhenjin
 * @date: 2020/10/30
 */
class HomeListAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    private var articleList: List<ArticleData> = ArrayList<ArticleData>()
    private val mContext = context
    fun setData(dataList: List<ArticleData>) {
        this.articleList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_list, parent, false)
        return MyViewHolder(itemView,mContext)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int = articleList.size ?: 0
}

class MyViewHolder(itemView: View,  context: Context) : RecyclerView.ViewHolder(itemView) {
    private var mContext = context
    fun bind(article: ArticleData) {

        itemView.tv_title.text = article.title
        itemView.tv_time.text = article.niceDate


        if (TextUtils.isEmpty(article.author)) {
            itemView.tv_share_user.text =  "推荐者：${article.shareUser}"
        } else {
            itemView.tv_share_user.text =  "作者：${article.author}"
        }

        itemView.setOnClickListener {
            WebViewWarpService.instance.start(mContext, article.title, article.link)
        }
    }
}
