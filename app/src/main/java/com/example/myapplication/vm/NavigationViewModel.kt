package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.NavigationRepository
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem
import kotlinx.coroutines.launch


class NavigationViewModel: BaseViewModel<NavigationRepository>() {

    private val  naItemLiveData = MutableLiveData<MutableList<NavigationItem>>()


    fun getNaItemLiveData(): MutableLiveData<MutableList<NavigationItem>> {
        return naItemLiveData
    }




    fun getNavItemData() {
        viewModelScope.launch {
            val tabData = repository.getNavData()
            if (tabData is ResponseResult.Success) {
                naItemLiveData.postValue(tabData.data)
            } else if (tabData is ResponseResult.Error) {
                val exception = tabData.exception
                Log.e("NavigationViewModel", exception.msg.toString())
            }
        }
    }
}