package com.pzj.wanandroid_kotlin.network.response

/**
 * @description: ResponseData
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
data class ResponseData<out T>(val errorCode: Int, val errorMsg: String, val data: T)