package com.example.myapplication.ui.fragment.tree

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.TreeListAdapter
import com.example.myapplication.vm.AboutViewModel
import com.example.myapplication.vm.TreeViewModel
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_tree.*

class TreeFragment : BaseVMFragment() {

    private lateinit var mAdapter: TreeListAdapter

    private lateinit var mRecyclerView: RecyclerView
    private val model: TreeViewModel by viewModels()

    override fun initView() {
        super.initView()
        mRecyclerView = recyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.home_list_divider)!!)
        mRecyclerView.addItemDecoration(decoration)
    }

    override fun initData() {
        model.apply { getTree() }
    }

    override fun startDataObserver() {
        model.getTreeLiveData().observe(this) {
            mAdapter = TreeListAdapter(requireActivity(), it)
            mRecyclerView.adapter = mAdapter
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tree
    }
}