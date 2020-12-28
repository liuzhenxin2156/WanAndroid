package com.example.myapplication.repository



import com.example.myapplication.modle.*
import com.pzj.wanandroid_kotlin.base.BaseRepository
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem

class SearchResultRepository : BaseRepository() {


    suspend fun getSearchResultData(page:Int,key:String): ResponseResult<SearchResultModel> {
        val tabData = getApiService().search(page,key)
        return callRequest(call = { handleResponse(tabData) })
    }
    
}