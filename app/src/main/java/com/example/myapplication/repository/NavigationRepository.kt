package com.example.myapplication.repository



import com.example.myapplication.modle.AboutTabData
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.ProjectTabItem
import com.pzj.wanandroid_kotlin.base.BaseRepository
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem

class NavigationRepository : BaseRepository() {


    suspend fun getNavData(): ResponseResult<MutableList<NavigationItem>> {
        val tabData = getApiService().getNavigationData()
        return callRequest(call = { handleResponse(tabData) })
    }
    
}