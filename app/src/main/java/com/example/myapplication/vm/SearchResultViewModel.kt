package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.HotKeyModel
import com.example.myapplication.modle.SearchResultModel
import com.example.myapplication.repository.HotKeyRepository
import com.example.myapplication.repository.NavigationRepository
import com.example.myapplication.repository.SearchResultRepository
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem
import kotlinx.coroutines.launch


class SearchResultViewModel: BaseViewModel<SearchResultRepository>() {

    private val searchResultLiveData = MutableLiveData<SearchResultModel>()

    fun getSearchResultLiveData(): MutableLiveData<SearchResultModel> {
        return searchResultLiveData
    }


    fun getSearchResult(page:Int,key:String) {
        viewModelScope.launch {
            val tabData = repository.getSearchResultData(page, key)
            if (tabData is ResponseResult.Success) {
                searchResultLiveData.postValue(tabData.data)
            } else if (tabData is ResponseResult.Error) {
                val exception = tabData.exception
                Log.e("getSearchResultLiveData", exception.msg.toString())
            }
        }
    }
}