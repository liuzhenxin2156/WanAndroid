package com.pzj.wanandroid_kotlin.repository

import com.example.myapplication.modle.Articles
import com.example.myapplication.modle.Banner
import com.pzj.wanandroid_kotlin.base.BaseRepository
import com.pzj.wanandroid_kotlin.network.response.ResponseResult

/**
 * @description: HomeRepository
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
class HomeRepository : BaseRepository() {

    /**
     * 获取首页banner
     */
    suspend fun getBannerList(): ResponseResult<List<Banner>> {
        val bannerList = getApiService().getBannerList()
        return callRequest(call = { handleResponse(bannerList) })
    }

    suspend fun getArticleData(pageNum: Int): ResponseResult<Articles> {
        val articleData = getApiService().getArticleData(pageNum)
        return callRequest(call = { handleResponse(articleData) })
    }
}