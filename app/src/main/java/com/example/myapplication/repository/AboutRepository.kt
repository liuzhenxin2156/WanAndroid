package com.example.myapplication.repository

import com.example.myapplication.modle.AboutTabData
import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.ProjectTabItem
import com.pzj.wanandroid_kotlin.base.BaseRepository
import com.pzj.wanandroid_kotlin.network.response.ResponseResult

class AboutRepository : BaseRepository() {


    suspend fun getTabData():ResponseResult< MutableList<ProjectTabItem>>{
        val tabData = getApiService().getTabData()
        return callRequest(call = { handleResponse(tabData) })
    }

    suspend fun getAboutTabData(count: Int,cid :Int):ResponseResult<AboutTabData>{
        val tabItemPageData = getApiService().getTabItemPageData(count, cid)
        return callRequest(call = {handleResponse(tabItemPageData)})
    }
}