package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modle.HotKeyModel
import com.example.myapplication.repository.HotKeyRepository
import com.example.myapplication.repository.NavigationRepository
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem
import kotlinx.coroutines.launch


class HotKeyViewModel: BaseViewModel<HotKeyRepository>() {

    private val  hotKeyLiveData = MutableLiveData<MutableList<HotKeyModel>>()


    fun getHotKeyLiveData(): MutableLiveData<MutableList<HotKeyModel>> {
        return hotKeyLiveData
    }




    fun getHotKey() {
        viewModelScope.launch {
            val tabData = repository.getHotKeyData()
            if (tabData is ResponseResult.Success) {
                hotKeyLiveData.postValue(tabData.data)
            } else if (tabData is ResponseResult.Error) {
                val exception = tabData.exception
                Log.e("hotKeyLiveData", exception.msg.toString())
            }
        }
    }
}