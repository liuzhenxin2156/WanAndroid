package com.example.myapplication.repository



import com.example.myapplication.modle.AboutTabData
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.HotKeyModel
import com.example.myapplication.modle.ProjectTabItem
import com.pzj.wanandroid_kotlin.base.BaseRepository
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem

class HotKeyRepository : BaseRepository() {


    suspend fun getHotKeyData(): ResponseResult<MutableList<HotKeyModel>> {
        val tabData = getApiService().getHotKey()
        return callRequest(call = { handleResponse(tabData) })
    }
    
}