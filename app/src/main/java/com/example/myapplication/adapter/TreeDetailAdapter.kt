package com.example.myapplication.adapter

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.modle.DatasBean
import com.example.myapplication.modle.HotKeyModel
import com.example.myapplication.modle.TreeDetailDatasBean
import com.win.lib_base.service.webview.warp.WebViewWarpService
import kotlinx.android.synthetic.main.hotkey_item.view.*
import kotlinx.android.synthetic.main.hotkey_item.view.hotkey
import kotlinx.android.synthetic.main.item_home_list.view.*
import kotlinx.android.synthetic.main.search_result_item.view.*
import kotlinx.android.synthetic.main.search_result_item.view.title
import kotlinx.android.synthetic.main.tree_detail_list_item.view.*

class TreeDetailAdapter(context: Context) :
        BaseRecyclerViewAdapter<TreeDetailDatasBean, TreeDetailAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.tree_detail_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setData(dataList[position])
    }


    class ViewHolder(
            itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        fun setData(datasBean: TreeDetailDatasBean) {

           itemView.title.text = datasBean.title
            itemView.desc.text  = datasBean.desc

            if (TextUtils.isEmpty(datasBean.author)) {
                itemView.author.text =  "推荐者：${datasBean.shareUser}"
            } else {
                itemView.author.text =  "作者：${datasBean.author}"
            }
            itemView.time.text  = datasBean.niceDate

        }

    }

}