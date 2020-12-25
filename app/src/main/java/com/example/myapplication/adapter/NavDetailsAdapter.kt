package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.win.ft_home.model.navigation.NavigationItemDetail
import kotlinx.android.synthetic.main.navigation_tab_page_adapter.view.*

class NavDetailsAdapter(context: Context) : BaseRecyclerViewAdapter<NavigationItemDetail, NavDetailsAdapter.ViewHolder>(
        context
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.navigation_tab_page_adapter, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setItemData(dataList[position])

    }

    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        fun setItemData(data: NavigationItemDetail) {
            itemView.tvName.text = data.title
        }

    }
}