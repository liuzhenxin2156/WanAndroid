package com.example.myapplication.ui.search.result

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.SearchHotKeyAdapter
import com.example.myapplication.adapter.SearchResultAdapter
import com.example.myapplication.vm.HotKeyViewModel
import com.example.myapplication.vm.SearchResultViewModel
import com.pzj.wanandroid_kotlin.base.BaseVMActivity
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_search_result.*
import kotlinx.android.synthetic.main.hotkey_page.*
import kotlinx.android.synthetic.main.hotkey_page.recycleView

/**
 * 搜索结果页面
 */
class SearchResultFragment:BaseVMFragment() {

    private val mode: SearchResultViewModel by viewModels()

    companion object {
        fun newInstance(hotKey: String): SearchResultFragment {
            val fragment = SearchResultFragment()
            val bundle = Bundle()
            bundle.putString("hotkey", hotKey)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initData() {
        val hotKey = requireArguments().getString("hotkey")
        mode.apply {
            if (hotKey != null) {
                getSearchResult(0,hotKey)
            }
        }
    }

    override fun startDataObserver() {
        mode.getSearchResultLiveData().observe(this){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val adapter = SearchResultAdapter(requireContext())
            val dataList = it
            adapter.dataList = dataList.datas
            recyclerView.adapter = adapter
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_search_result


}