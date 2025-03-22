package com.example.myapplication3.view.ui.activities.home.components

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.myapplication3.view.styles.TextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar(navController: NavHostController,name : String) {
    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xff0d253f),
            navigationIconContentColor = Color.White),
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(name, style = TextStyles().textStyleBold14.copy(color = Color.White))
            }
        },
        navigationIcon = {
            IconButton({
                (context as Activity).finish()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "menu items"
                )
            }
        },

        )
}