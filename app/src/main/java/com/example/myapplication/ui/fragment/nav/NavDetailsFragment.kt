package com.example.myapplication.ui.fragment.nav


import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.NavDetailsAdapter
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pzj.wanandroid_kotlin.base.BaseVMFragment

import com.win.ft_home.model.navigation.NavigationItemDetail
import com.win.lib_base.service.webview.warp.WebViewWarpService
import kotlinx.android.synthetic.main.navigation_data_page.*


class NavDetailsFragment : BaseVMFragment() {



    private var detailList: MutableList<NavigationItemDetail>? = null

    private val gson = Gson()

    companion object {
        fun newInstance(
            articles: MutableList<NavigationItemDetail>,
            gson: Gson,
        ): NavDetailsFragment {

            val fragment = NavDetailsFragment()
            val bundle = Bundle()
            bundle.putString("data", gson.toJson(articles))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
            return R.layout.navigation_data_page
    }

    override fun initData() {
        val arguments = requireArguments()
        val data = arguments.getString("data")

        detailList = gson.fromJson<MutableList<NavigationItemDetail>>(
            data,
            object : TypeToken<MutableList<NavigationItemDetail>>() {}.type
        )
    }


    override fun startDataObserver() {
       recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = NavDetailsAdapter(requireContext())
        adapter.dataList = detailList!!
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object :
            BaseRecyclerViewAdapter.OnItemClickListener<NavigationItemDetail> {
            override fun onItemClick(item: NavigationItemDetail, position: Int) {
                WebViewWarpService.instance.start(requireContext(), item.title, item.link)
            }
        })
    }
}