package com.pzj.wanandroid_kotlin.base

import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

/**
 * @desc: BaseViewModel
 * @author: pengzhenjin
 * @date: 2020/10/28
 */
open class BaseViewModel<T : BaseRepository> : ViewModel() {

    val repository: T by lazy {
        val clazz =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        clazz.getDeclaredConstructor().newInstance()
    }

}