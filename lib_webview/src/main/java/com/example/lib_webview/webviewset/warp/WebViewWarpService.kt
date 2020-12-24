package com.win.lib_base.service.webview.warp

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib_webview.Constants

import com.win.lib_base.service.webview.WebViewService

/**
 * Create by liwen on 2020/5/26
 */
class WebViewWarpService private constructor() {

    @Autowired(name = Constants.WEB_VIEW_SERVICE_PATH)
    lateinit var service: WebViewService


    init {
        ARouter.getInstance().inject(this)
    }

    companion object {

        val instance = Singleton.holder

        object Singleton {
            val holder = WebViewWarpService()
        }

    }

    fun start(context: Context, title: String, url: String) {
        service.start(context, title, url)
    }

}