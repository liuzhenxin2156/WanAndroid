package com.example.myapplication.ui.main

import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.example.myapplication.R


import com.pzj.wanandroid_kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity :BaseActivity() {


    override fun getLayoutId(): Int {
      return  R.layout.activity_setting

    }

    override fun initView() {
        super.initView()
        button.setOnClickListener {
            Toast.makeText(this,"GOGOGO",Toast.LENGTH_SHORT).show()
        }
    }
}