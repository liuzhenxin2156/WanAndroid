package com.pzj.wanandroid_kotlin.network.exception

/**
 * @description: ResultException
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
class ResultException(var errCode: String?, var msg: String?) : Exception(msg) {

}
