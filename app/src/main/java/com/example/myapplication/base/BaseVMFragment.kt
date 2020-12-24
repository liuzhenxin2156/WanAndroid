package com.pzj.wanandroid_kotlin.base

import android.os.Bundle
import android.view.View

/**
 * @description: BaseVMFragment
 * @author: pengzhenjin
 * @date: 2020/10/30
 */
abstract class BaseVMFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        startDataObserver()
    }

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 开启数据观察者
     */
    abstract fun startDataObserver()
}