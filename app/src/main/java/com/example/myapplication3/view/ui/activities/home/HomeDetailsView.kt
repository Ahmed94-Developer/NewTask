package com.example.myapplication3.view.ui.activities.home

import android.R.attr.rating
import android.annotation.SuppressLint
import android.app.Activity
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.example.myapplication3.view.styles.TextStyles
import com.example.myapplication3.view.ui.activities.home.components.AddAppBar
import com.example.myapplication3.view.ui.activities.home.components.StarRatingBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDetails(navController: NavHostController,name : String,description : String,image : String,votes : Double){
    var rating by remember { mutableStateOf(votes) }
    Scaffold(
        topBar = {AddAppBar(navController,name)},
        containerColor = Color.White,
        content = {innerPadding ->
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Box(modifier = Modifier.height(height = 40.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500"+image)
                        .crossfade(true)
                        .scale(scale = Scale.FIT)
                        .build(),
                    contentDescription = "1213",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(
                            top = 16.dp, start = 8.dp, end = 8.dp
                        )
                        .clip(shape = RoundedCornerShape(size = 8.dp)),)
                Box(modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)) {
                    StarRatingBar(
                        maxStars = 10,
                        rating = rating.toFloat(),
                        onRatingChanged = {
                            rating = it.toDouble()
                        }
                    )
                }
                Text(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                    text = description, style = TextStyles().textStyleNormal12.copy(color = Color(0xff0d253f)))
            }
        }
    )
}
