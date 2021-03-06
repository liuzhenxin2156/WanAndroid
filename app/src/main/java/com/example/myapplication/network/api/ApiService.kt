package com.pzj.wanandroid_kotlin.network.api

import com.example.myapplication.modle.*
import com.pzj.wanandroid_kotlin.network.response.ResponseData

import com.win.ft_home.model.navigation.NavigationItem

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @description: ApiService
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
interface ApiService {
    /**
     * 获取首页banner
     */
    @GET("/banner/json")
    suspend fun getBannerList(): ResponseData<List<Banner>>

    /**
     * 获取首页文章数据，支持分页
     */
    @GET("/article/list/{pageNum}/json")
    suspend fun getArticleData(@Path("pageNum") pageNum: Int): ResponseData<Articles>

    /**
     * 获取关注item
     */
    @GET("/project/tree/json")
    suspend fun getTabData():ResponseData< MutableList<ProjectTabItem>>



    @GET("/project/list/{count}/json")
    suspend fun getTabItemPageData(
            @Path("count") count: Int,
            @Query("cid") cid: Int
    ): ResponseData<AboutTabData>


    /**
     * 获取导航数据
     */
    @GET("/navi/json")
    suspend fun getNavigationData(): ResponseData<MutableList<NavigationItem>>

    @GET("/hotkey/json")
    suspend fun getHotKey(): ResponseData<MutableList<HotKeyModel>>


    @POST(" /article/query/{page}/json")
    suspend fun search(
            @Path("page") page: Int,
            @Query("k") key: String
    ): ResponseData<SearchResultModel>


    @GET("/tree/json")
    suspend fun getTreeList(): ResponseData<MutableList<TreeData>>

    @GET(value = "/article/list/{count}/json")
    suspend fun getTreeDetailList(
        @Path("count") count: Int,
        @Query("cid") cid: Int
    ): ResponseData<TreeDetailModel>


}

