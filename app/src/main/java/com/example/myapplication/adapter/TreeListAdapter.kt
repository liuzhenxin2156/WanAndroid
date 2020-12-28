package com.example.myapplication.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modle.TreeData
import com.example.myapplication.ui.treeDetails.TreeDetailsActivity
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter
import kotlinx.android.synthetic.main.search_result_item.view.*
import kotlinx.android.synthetic.main.tree_list_item.view.*

class TreeListAdapter(context: Context, data: MutableList<TreeData>) :
        RecyclerView.Adapter<TreeListAdapter.ViewHolder>() {

    private val mContext = context
    private var mData: MutableList<TreeData> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.tree_list_item, parent, false)
        return ViewHolder(itemView,mData)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(mData[position], mContext)
    }

    class ViewHolder(
            itemView: View,mList: MutableList<TreeData>) : RecyclerView.ViewHolder(itemView) {
        private var mData: MutableList<TreeData> = mList;
        fun setData(
                treeDataItem: TreeData,
                mContext: Context
        ) {
            val tagChildren = treeDataItem.children
            itemView.mTvTitle.text =treeDataItem.name
            itemView.mFlowLayout.setAdapter(object : TagAdapter() {
                override fun getItemCount(): Int {
                    return tagChildren.size
                }

                override fun createView(
                        inflater: LayoutInflater,
                        parent: ViewGroup,
                        position: Int
                ): View {
                    return inflater.inflate(R.layout.flow_layout_item, parent, false)
                }

                override fun bindView(view: View, position: Int) {

                    (view as TextView).text = tagChildren[position].name

                }

                override fun onItemViewClick(view: View, position: Int) {

                    val item = tagChildren[position]
                    TreeDetailsActivity.start(mContext,item.id,item.name)

                }

            })
        }

    }
}