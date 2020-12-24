package com.example.myapplication.modle

data class ProjectTabItem(var id: Int,var name:String) {

    override fun toString(): String {
        return "ProjectTabItem(id=$id, name='$name')"
    }
}