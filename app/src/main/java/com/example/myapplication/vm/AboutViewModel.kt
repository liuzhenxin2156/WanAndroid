package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.Banner
import com.example.myapplication.modle.ProjectTabItem
import com.example.myapplication.repository.AboutRepository
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import kotlinx.coroutines.launch

class AboutViewModel: BaseViewModel<AboutRepository>() {

    private val  tabItemLiveData = MutableLiveData<MutableList<ProjectTabItem>>()


    fun getTabItemLiveData(): MutableLiveData< MutableList<ProjectTabItem>> {
        return tabItemLiveData
    }




    fun getBannerList() {
        viewModelScope.launch {
        val tabData = repository.getTabData()
            if (tabData is ResponseResult.Success) {
                tabItemLiveData.postValue(tabData.data)
            } else if (tabData is ResponseResult.Error) {
                val exception = tabData.exception
                Log.e("HomeViewModel", exception.msg.toString())
            }
        }
    }
}