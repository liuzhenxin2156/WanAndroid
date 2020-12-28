package com.example.myapplication.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modle.AboutTabData
import com.example.myapplication.modle.HotKeyModel
import com.example.myapplication.modle.TreeData
import com.example.myapplication.modle.TreeDetailModel
import com.example.myapplication.repository.HotKeyRepository
import com.example.myapplication.repository.NavigationRepository
import com.example.myapplication.repository.TreeRepository
import com.pzj.wanandroid_kotlin.base.BaseViewModel
import com.pzj.wanandroid_kotlin.network.response.ResponseResult
import com.win.ft_home.model.navigation.NavigationItem
import kotlinx.coroutines.launch


class TreeViewModel: BaseViewModel<TreeRepository>() {

    private val  treeLiveData = MutableLiveData<MutableList<TreeData>>()

    private val  treeDetails = MutableLiveData<TreeDetailModel>()


    fun getTreeLiveData(): MutableLiveData<MutableList<TreeData>> {
        return treeLiveData
    }


    fun getTreeDetailsLive(): MutableLiveData<TreeDetailModel> {
        return treeDetails
    }


    fun getTree() {
        viewModelScope.launch {
            val tabData = repository.getTreeData()
            if (tabData is ResponseResult.Success) {
                treeLiveData.postValue(tabData.data)
            } else if (tabData is ResponseResult.Error) {
                val exception = tabData.exception
            }
        }
    }


    fun getDetails(pageNum: Int,cid :Int) {
        viewModelScope.launch {
            val aboutTabData = repository.getDetailsData(pageNum,cid)
            if (aboutTabData is ResponseResult.Success) {
                treeDetails.postValue(aboutTabData.data)
                val data = aboutTabData.data
            } else if (aboutTabData is ResponseResult.Error) {
                val exception = aboutTabData.exception
            }
        }
    }
}