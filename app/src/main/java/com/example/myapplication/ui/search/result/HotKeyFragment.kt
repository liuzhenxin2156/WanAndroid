package com.example.myapplication.ui.search.result

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.NavDetailsAdapter
import com.example.myapplication.adapter.NavItemAdapter
import com.example.myapplication.adapter.SearchHotKeyAdapter
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.modle.HotKeyModel
import com.example.myapplication.ui.search.SearchActivity
import com.example.myapplication.vm.HotKeyViewModel
import com.example.myapplication.vm.NavigationViewModel
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.hotkey_page.*
import kotlinx.android.synthetic.main.navigation_data_page.*

/**
 * 热门搜索 词语
 */
class HotKeyFragment :BaseVMFragment()  {

    private val mode: HotKeyViewModel by viewModels()


    companion object {
        fun newInstance(): HotKeyFragment {
            return HotKeyFragment()
        }
    }
    override fun initData() {
        mode.apply { getHotKey() }
    }

    override fun startDataObserver() {
        mode.getHotKeyLiveData().observe(this){
            recycleView.layoutManager = GridLayoutManager(requireContext(),2)
            val adapter = SearchHotKeyAdapter(requireContext())
            val dataList = it
            adapter.dataList = dataList!!
            recycleView.adapter = adapter

            adapter.setOnItemClickListener(object :
                    BaseRecyclerViewAdapter.OnItemClickListener<HotKeyModel> {
                override fun onItemClick(item: HotKeyModel, position: Int) {
                    (requireActivity() as SearchActivity).setHotKeyInputSearch(item.name)
                }
            })
        }

    }

    override fun getLayoutId(): Int {
        return  R.layout.hotkey_page
    }
}