package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modle.AboutTabData
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.Banner
import com.example.myapplication.repository.AboutRepository
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import kotlinx.coroutines.launch

class ItemTabViewModel : BaseViewModel<AboutRepository>() {


    private val aboutTabDataLiveData = MutableLiveData<AboutTabData>()
    fun getItemTabLive(): MutableLiveData<AboutTabData> {
        return aboutTabDataLiveData
    }



    fun getItemTab(pageNum: Int,cid :Int) {
        viewModelScope.launch {
            val aboutTabData = repository.getAboutTabData(pageNum,cid)
            if (aboutTabData is ResponseResult.Success) {
                aboutTabDataLiveData.postValue(aboutTabData.data)
                val data = aboutTabData.data
            } else if (aboutTabData is ResponseResult.Error) {
                val exception = aboutTabData.exception
                Log.e("ItemTabViewModel", exception.msg.toString())
            }
        }
    }
}