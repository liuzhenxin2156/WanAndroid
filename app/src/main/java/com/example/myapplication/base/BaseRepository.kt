package com.pzj.wanandroid_kotlin.base

import com.pzj.wanandroid_kotlin.network.RetrofitClient
import com.pzj.wanandroid_kotlin.network.api.ApiService
import com.pzj.wanandroid_kotlin.network.exception.DealException
import com.pzj.wanandroid_kotlin.network.exception.ResultException
import com.pzj.wanandroid_kotlin.network.response.ResponseData
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

/**
 * @description: BaseRepository
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
open class BaseRepository {

    /**
     * 获取 ApiService
     */
    fun getApiService(): ApiService {
        return RetrofitClient.instance.create(ApiService::class.java)
    }

    /**
     * 请求回调，异常处理
     */
    suspend fun <T : Any> callRequest(call: suspend () -> ResponseResult<T>): ResponseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Error(DealException.handlerException(e))
        }
    }

    /**
     * 处理响应数据
     */
    suspend fun <T : Any> handleResponse(
        response: ResponseData<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): ResponseResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                ResponseResult.Error(
                    ResultException(response.errorCode.toString(), response.errorMsg)
                )
            } else {
                successBlock?.let { it() }
                ResponseResult.Success(response.data)
            }
        }
    }
}