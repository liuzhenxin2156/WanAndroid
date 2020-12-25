package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.AboutTabAdapter
import com.example.myapplication.vm.ItemTabViewModel
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_tab_item.*


class TabItemFragment :BaseVMFragment() {
    private var pageNum: Int = 10
    private var cid:Int = 0
    private lateinit var adapter: AboutTabAdapter
    private val mode: ItemTabViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_tab_item

    companion object {
        fun newInstance(id: Int): TabItemFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment = TabItemFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun initData() {
        val arguments = requireArguments()
        cid = arguments.getInt("id")
        mode.apply { getItemTab(pageNum,cid) }
    }

    override fun initView() {
        super.initView()
        recycler_about_item.layoutManager = LinearLayoutManager(requireContext());
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recycler_about_item.addItemDecoration(decoration)
        adapter = AboutTabAdapter(requireContext())
        recycler_about_item.adapter = adapter


    }
    override fun startDataObserver() {
        mode.getItemTabLive().observe(this){
         val datas = it.datas
            adapter.setData(datas)
        }

    }
}