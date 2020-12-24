package com.example.lib_webview.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_webview.Constants
import com.example.lib_webview.webview.WebViewActivity
import com.win.lib_base.service.webview.WebViewService
@Route(path = Constants.WEB_VIEW_SERVICE_PATH)
class WebViewServiceImpl : WebViewService {

    override fun start(context: Context, title: String, url: String) {
        WebViewActivity.start(context, title, url)
    }

    override fun init(context: Context?) {

    }
}