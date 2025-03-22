package com.example.myapplication3.domain.entities

import androidx.paging.compose.LazyPagingItems

data class TabData(val title : String,val selectedIcon : Int
,val unSelectedIcon: Int,val lazyPagingItems: LazyPagingItems<Result>) {
}