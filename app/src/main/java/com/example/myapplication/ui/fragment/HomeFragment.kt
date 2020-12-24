package com.example.myapplication.ui.fragment

import android.R.attr
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.HomeBannerAdapter
import com.example.myapplication.vm.HomeViewModel
import com.pzj.wanandroid_kotlin.adapter.HomeListAdapter
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseVMFragment() {

    private var pageNum: Int = 0
    private lateinit var adapter: HomeListAdapter

    val mList: ArrayList<String> = arrayListOf()
    private val mode: HomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        super.initView()
        recyclerView.layoutManager = LinearLayoutManager(requireContext());
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        adapter = HomeListAdapter(requireContext())
        recyclerView.adapter = adapter


    }

    override fun initData() {
        mode.apply { getArticleData(pageNum) }
        mode.apply { getBannerList() }
    }

    override fun startDataObserver() {
        mode.getArticleLiveData().observe(this) {
            val dataList = it.datas
            adapter.setData(dataList)
        }

        mode.getBannerLiveData().observe(this){
           val  dataList = it
            banner.adapter = HomeBannerAdapter(dataList)
            Log.d("lzx------》", dataList.toString())
            Log.d("lzx------》", mList.toString() + " 轮播图路劲 ")
        }
    }

    override fun onStart() {
        super.onStart()
        banner.start()
    }
  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  override fun onStop() {
        super.onStop()
        //停止轮播
        attr.banner
    }

     @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
     override fun onDestroy() {
        super.onDestroy()
        //销毁
        attr.banner
    }
}