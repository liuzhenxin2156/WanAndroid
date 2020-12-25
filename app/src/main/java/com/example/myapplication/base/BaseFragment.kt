package com.pzj.wanandroid_kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @desc: BaseFragment
 * @author: pengzhenjin
 * @date: 2020/10/28
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
    }

    /**
     * 获取布局文件id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化 View
     */
    open fun initView() {}

    /**
     * 初始化事件
     */
    open fun initEvent() {}
}