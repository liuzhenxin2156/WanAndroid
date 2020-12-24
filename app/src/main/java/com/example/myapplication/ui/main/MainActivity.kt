package com.example.myapplication.ui.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_webview.Constants
import com.example.myapplication.R
import com.pzj.wanandroid_kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 路由跳转，解决组件化问题
 */
@Route(path = Constants.LOGIN_SERVICE_PATH)
class MainActivity : BaseActivity() {



    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun initEvent() {
        super.initEvent()
    }
}