package com.pzj.wanandroid_kotlin.network.response

import com.pzj.wanandroid_kotlin.network.exception.ResultException

/**
 * @description: ResponseResult
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
sealed class ResponseResult<out T : Any> {

    /**
     * 成功
     */
    data class Success<out T : Any>(val data: T) : ResponseResult<T>()

    /**
     * 失败，异常处理
     */
    data class Error(val exception: ResultException) : ResponseResult<Nothing>()
}