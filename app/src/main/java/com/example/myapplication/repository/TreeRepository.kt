package com.example.myapplication.repository



import com.example.myapplication.modle.*
import com.pzj.wanandroid_kotlin.base.BaseRepository
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem

class TreeRepository : BaseRepository() {


    suspend fun getTreeData(): ResponseResult<MutableList<TreeData>> {
        val tabData = getApiService().getTreeList()
        return callRequest(call = { handleResponse(tabData) })
    }


    suspend fun getDetailsData(count: Int,cid :Int):ResponseResult<TreeDetailModel>{
        val tabItemPageData = getApiService().getTreeDetailList(count, cid)
        return callRequest(call = {handleResponse(tabItemPageData)})
    }
}