package com.example.myapplication.ui.fragment.nav


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapter.NavItemAdapter
import com.example.myapplication.adapter.NavigationTabItemSelectedListener
import com.example.myapplication.utils.DensityUtil
import com.example.myapplication.vm.NavigationViewModel
import com.google.gson.Gson
import com.pzj.wanandroid_kotlin.base.BaseVMFragment

import com.win.ft_home.model.navigation.NavigationItem
import kotlinx.android.synthetic.main.fragment_navigation.*


class NavigationFragment : BaseVMFragment() {
    private val mode: NavigationViewModel by viewModels()
    private lateinit var adapter: NavItemAdapter

    private var LAST_ITEM_COUNT_LIMIT: Int = 0
    private var TAB_ITEM_OFFSET_TOP: Int = DensityUtil.dip2px(50f)
    private var TAB_ITEM_OFFSET_BOTTOM: Int = DensityUtil.dip2px(50f)
    private var mPrePosition = 0
    private var heightPixels: Int = 0

    private val gson = Gson()

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun initView() {
        super.initView()
        tabRecycleView.layoutManager = LinearLayoutManager(requireContext());





        viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        viewPager2.registerOnPageChangeCallback(viewPager2Callback)
        calculateMoveSize()
    }

    override fun initData() {
        mode.apply { getNavItemData() }
    }

    override fun startDataObserver() {
        mode.getNaItemLiveData().observe(this) {
            val dateList = it;
          //
            performTabRecycleView(dateList)
            performViewPagerData(dateList)
        }

    }

    private fun calculateMoveSize() {
        heightPixels = requireActivity().resources.displayMetrics.heightPixels
        heightPixels = ((heightPixels - (TAB_ITEM_OFFSET_TOP * 2).toFloat()) / 2).toInt()
        LAST_ITEM_COUNT_LIMIT = (heightPixels / TAB_ITEM_OFFSET_TOP.toFloat()).toInt()
    }
    private fun performTabRecycleView(item: MutableList<NavigationItem>) {

        adapter = NavItemAdapter(requireContext(),item)
        tabRecycleView.adapter = adapter
        adapter.setItemSelectedListener(object : NavigationTabItemSelectedListener {
            override fun onItemSelected(itemSub: NavigationItem, position: Int) {
                adapter.setItemPositionSelected(position)
              //  adapter.notifyDataSetChanged()
                viewPager2.setCurrentItem(position, true)

            }
        })
    }
    private fun performViewPagerData(
        item: MutableList<NavigationItem>
    ) {

        viewPager2.adapter =
            object : FragmentStateAdapter(requireActivity().supportFragmentManager, lifecycle) {
                override fun getItemCount(): Int {
                    return item.size
                }

                override fun createFragment(position: Int): Fragment {
                    return NavDetailsFragment.newInstance(item[position].articles,gson)
                }

            }

    }
    /**
     * viewPager监听
     */
    private val viewPager2Callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            performMoveEvent(position)
            adapter.setItemPositionSelected(position)
            adapter.notifyDataSetChanged()

        }
    }


    /**
     * 处理viewpager往下滑动 索引
     */
    private fun performMoveEvent(position: Int) {

        if (position > mPrePosition) {  //向下滑动

            if (position * TAB_ITEM_OFFSET_TOP
                + TAB_ITEM_OFFSET_TOP > heightPixels
            ) {
                tabRecycleView.smoothScrollBy(0, TAB_ITEM_OFFSET_TOP)
            }

        } else { //向上滑动

            if (position * TAB_ITEM_OFFSET_BOTTOM
                + TAB_ITEM_OFFSET_BOTTOM * LAST_ITEM_COUNT_LIMIT > heightPixels
                && position < (viewPager2.adapter!!.itemCount - LAST_ITEM_COUNT_LIMIT)
            ) {

                if (position < LAST_ITEM_COUNT_LIMIT) {
                    TAB_ITEM_OFFSET_BOTTOM = TAB_ITEM_OFFSET_BOTTOM * LAST_ITEM_COUNT_LIMIT
                } else {
                    TAB_ITEM_OFFSET_BOTTOM = TAB_ITEM_OFFSET_TOP
                }

                tabRecycleView.smoothScrollBy(
                    0,
                    -TAB_ITEM_OFFSET_BOTTOM
                )

            }
        }

        mPrePosition = position
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewPager2.unregisterOnPageChangeCallback(viewPager2Callback)
    }
}