package com.example.myapplication.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.modle.DatasBean
import com.example.myapplication.modle.HotKeyModel
import kotlinx.android.synthetic.main.hotkey_item.view.*
import kotlinx.android.synthetic.main.hotkey_item.view.hotkey
import kotlinx.android.synthetic.main.search_result_item.view.*

class SearchResultAdapter(context: Context) :
        BaseRecyclerViewAdapter<DatasBean, SearchResultAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setData(dataList[position])
    }


    class ViewHolder(
            itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        fun setData(datasBean: DatasBean) {

           itemView.title.text = Html.fromHtml(datasBean.title)
        }

    }

}