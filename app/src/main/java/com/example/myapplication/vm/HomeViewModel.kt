package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.Banner
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.pzj.wanandroid_kotlin.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeRepository>() {

   // private val bannerLiveData = MutableLiveData<List<Banner>>()
    private val articleLiveData = MutableLiveData<Articles>()
    private val  bannerLiveData = MutableLiveData<List<Banner>>()


    fun getBannerLiveData(): MutableLiveData<List<Banner>> {
        return bannerLiveData
    }

    fun getArticleLiveData(): MutableLiveData<Articles> {
        return articleLiveData
    }


    fun getBannerList() {
        viewModelScope.launch {
            val bannerList = repository.getBannerList()
            if (bannerList is ResponseResult.Success) {
                bannerLiveData.postValue(bannerList.data)
            } else if (bannerList is ResponseResult.Error) {
                val exception = bannerList.exception
                Log.e("HomeViewModel", exception.msg.toString())
            }
        }
    }

    fun getArticleData(pageNum: Int) {
        viewModelScope.launch {
            val articleData = repository.getArticleData(pageNum)
            if (articleData is ResponseResult.Success) {
                articleLiveData.postValue(articleData.data)
            } else if (articleData is ResponseResult.Error) {
                val exception = articleData.exception
                Log.e("HomeViewModel", exception.msg.toString())

            }
        }
    }

}