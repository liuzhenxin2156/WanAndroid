package com.example.myapplication.adapter

import com.win.ft_home.model.navigation.NavigationItem

interface NavigationTabItemSelectedListener {
    fun onItemSelected(item: NavigationItem, position: Int)
}