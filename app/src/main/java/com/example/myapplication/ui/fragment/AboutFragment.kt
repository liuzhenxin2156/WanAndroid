package com.example.myapplication.ui.fragment

import android.graphics.Typeface
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.modle.ProjectTabItem
import com.example.myapplication.vm.AboutViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_about.*


/**
 *
 */
class AboutFragment : BaseVMFragment() {
    private lateinit var mediator: TabLayoutMediator
    private var mData: MutableList<ProjectTabItem>? = null
    private val mode: AboutViewModel by viewModels()
    override fun initData() {
        mode.apply { getBannerList() }
    }

    override fun startDataObserver() {
            mode.getTabItemLiveData().observe(this){
                mData=  it;
                initView()
            }
    }


    override fun getLayoutId(): Int  =R.layout.fragment_about

    override fun initView() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView as TextView
                customView.textSize = 14f
                customView.typeface = Typeface.DEFAULT
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView as TextView
                customView.textSize = 16f
                customView.typeface = Typeface.DEFAULT_BOLD
            }

        })
        viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        viewPager.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                if (mData != null && mData!!.size > 0) {
                    return mData!!.size
                }
                return 0
            }

            override fun createFragment(position: Int): Fragment {
                val item = mData!![position]
                return createItemFragment(item.id)
            }

        }
        mediator = TabLayoutMediator(
                tabLayout,
                viewPager,
                true,
                TabLayoutMediator.TabConfigurationStrategy { tab, position -> //创建tab
                    tab.customView = createTabView(position)
                })
        mediator.attach()
    }

    private fun createItemFragment(id: Int): Fragment {
        return TabItemFragment.newInstance(id)
    }

    private fun createTabView(position: Int): View {
        if (mData != null && mData!!.size > 0) {
            val item = mData!![position]
            val textView = TextView(requireContext())
            textView.text = Html.fromHtml(item.name)
            return textView
        }

        return TextView(requireContext())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator.detach()
    }
}