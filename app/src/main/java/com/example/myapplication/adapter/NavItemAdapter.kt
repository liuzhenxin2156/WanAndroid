package com.example.myapplication.adapter

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modle.DataX
import com.win.ft_home.model.navigation.NavigationItem
import com.win.lib_base.service.webview.warp.WebViewWarpService
import com.win.lib_image_loader.app.ImageLoaderManager
import kotlinx.android.synthetic.main.fragment_about_tab.view.*
import kotlinx.android.synthetic.main.navigation_tab_item.view.*

class NavItemAdapter (context: Context, data: MutableList<NavigationItem>) :
    RecyclerView.Adapter<NavItemAdapter.ViewHolder>() {

    private val mContext = context
    private val inflater = LayoutInflater.from(mContext)
    private val mData = data

    private lateinit var mListener: NavigationTabItemSelectedListener

    fun setItemSelectedListener(listener: NavigationTabItemSelectedListener) {
        this.mListener = listener
    }

    private var isSelectedPosition = 0

    fun setItemPositionSelected(position: Int) {
        isSelectedPosition = position
    }

    fun getItemPositionSelected(): Int {
        return isSelectedPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.navigation_tab_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(mData[position])
    }


    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {


        fun setData(
            item: NavigationItem
        ) {

            itemView.name.text = item.name
            if (adapterPosition == getItemPositionSelected()) {
                itemView.name.isSelected = true
                itemView.name.typeface = Typeface.DEFAULT_BOLD
            } else {
                itemView.name.isSelected = false
                itemView.name.typeface = Typeface.DEFAULT
            }


            itemView.name.setOnClickListener {
                mListener.onItemSelected(item, adapterPosition)
            }
        }
    }


}
