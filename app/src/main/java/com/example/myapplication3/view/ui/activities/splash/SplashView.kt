package com.example.myapplication3.view.ui.activities.splash

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication3.R
import com.example.myapplication3.view.ui.activities.home.HomeActivity
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(){
    val bg = Color(0xff0d253f)
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        delay(3000)
        context.startActivity(Intent(context, HomeActivity::class.java))
        (context as Activity).finish()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(bg),
        contentAlignment = Alignment.Center,
    ){
        Image(painter = painterResource(id = R.drawable.moviedb)
            , contentDescription = "1234")


    }

}