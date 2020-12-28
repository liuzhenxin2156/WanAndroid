package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.modle.HotKeyModel
import kotlinx.android.synthetic.main.hotkey_item.view.*

class SearchHotKeyAdapter(context: Context) :
        BaseRecyclerViewAdapter<HotKeyModel, SearchHotKeyAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.hotkey_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setData(dataList[position])
    }


    class ViewHolder(
            itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        fun setData(hotKeyModel: HotKeyModel) {
           itemView.hotkey.text = hotKeyModel.name
        }

    }

}