package com.example.myapplication.ui.treeDetails

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.SearchResultAdapter
import com.example.myapplication.adapter.TreeDetailAdapter
import com.example.myapplication.vm.HotKeyViewModel
import com.example.myapplication.vm.TreeViewModel
import com.pzj.wanandroid_kotlin.base.BaseVMActivity
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.activity_tree_details.*
import kotlinx.android.synthetic.main.fragment_search_result.*

class TreeDetailsActivity:BaseVMActivity() {
    private val mode: TreeViewModel by viewModels()
    private var title: String? =null
    private var cid:Int = 0
    companion object {
        fun start(context: Context, cid: Int, title: String) {
            val intent = Intent(context, TreeDetailsActivity::class.java)
            intent.putExtra("cid", cid)
            intent.putExtra("title", title)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        title =    intent.getStringExtra("title")
        cid  = intent.getIntExtra("cid",-1)
        mIvBack.setOnClickListener{
            finish()
        }
        mTvTitle.text =title
    }
    override fun initData() {
        mode.apply { getDetails(0,cid) }
    }

    override fun startDataObserver() {
        mode.getTreeDetailsLive().observe(this){
            recycleView.layoutManager = LinearLayoutManager(this)
            val adapter = TreeDetailAdapter(this)
            val dataList = it
            adapter.dataList = dataList.datas
            recycleView.adapter = adapter
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_tree_details

}