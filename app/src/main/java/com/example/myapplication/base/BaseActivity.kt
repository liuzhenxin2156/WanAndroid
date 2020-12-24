package com.pzj.wanandroid_kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.example.myapplication.utils.StatusBarKt

/**
 * @desc: BaseActivity
 * @author: pengzhenjin
 * @date: 2020/10/28
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        StatusBarKt.fitSystemBar(this)
            // ARouter.getInstance().inject(this);
        initView()
        initEvent()

    }

    override fun onDestroy() {
        super.onDestroy()
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