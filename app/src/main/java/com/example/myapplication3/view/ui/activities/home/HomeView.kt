package com.example.myapplication3.view.ui.activities.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import com.example.myapplication3.domain.entities.TabData
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import coil3.size.Scale
import com.commandiron.compose_loading.Pulse
import com.example.myapplication3.R
import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.view.ui.activities.home.components.ErrorMessage
import com.example.myapplication3.view.ui.activities.home.components.LoadingNextPageItem
import com.example.myapplication3.view.ui.activities.home.components.NowPlayingList
import com.example.myapplication3.view.ui.activities.home.components.PopularList
import com.example.myapplication3.view.ui.activities.home.components.UpComingList
import com.example.myapplication3.view.viewModels.NowPlayingViewModel
import com.example.myapplication3.view.viewModels.PopularViewModel
import com.example.myapplication3.view.viewModels.UpComingViewModel
import java.nio.file.Files.size

@SuppressLint("RememberReturnType", "IntentWithNullActionLaunch")
@Preview
@Composable
fun HomeScreen() {
    val nowPlayingViewModel: NowPlayingViewModel = hiltViewModel()
    val popularViewModel: PopularViewModel = hiltViewModel()
    val upcomingViewModel: UpComingViewModel = hiltViewModel()

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }


    val moviePagingItems: LazyPagingItems<Result> =
        nowPlayingViewModel.moviesState.collectAsLazyPagingItems()

    val popularPagingItems: LazyPagingItems<Result> =
        popularViewModel.moviesState.collectAsLazyPagingItems()

    val upcomingPagingItems: LazyPagingItems<Result> =
        upcomingViewModel.moviesState.collectAsLazyPagingItems()


    val tabItems = listOf(
        TabData(
            title = "Now Playing",
            selectedIcon = R.drawable.playing,
            unSelectedIcon = R.drawable.playing,
            lazyPagingItems = moviePagingItems
        ),
        TabData(
            title = "Popular",
            selectedIcon = R.drawable.star,
            unSelectedIcon = R.drawable.star,
            lazyPagingItems = popularPagingItems
        ),
        TabData(
            title = "UpComing",
            selectedIcon = R.drawable.upcoming,
            unSelectedIcon = R.drawable.upcoming,
            lazyPagingItems = upcomingPagingItems
        ),
    )
    TabRowComposeExampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {


            val pagerState = rememberPagerState {
                tabItems.size
            }
            LaunchedEffect(key1 = selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }
            LaunchedEffect(
                key1 = pagerState.currentPage,
                key2 = pagerState.isScrollInProgress
            ) {
                if (!pagerState.isScrollInProgress) {
                    selectedTabIndex = pagerState.currentPage
                }

            }
            Column(Modifier.fillMaxSize()) {

                TabRow(selectedTabIndex = selectedTabIndex) {

                    tabItems.forEachIndexed { index, item ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = {
                                selectedTabIndex = index
                            },
                            text = {
                                Text(
                                    text = item.title, textAlign = TextAlign.Center,
                                    style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.poppins)))
                                )
                            },
                            icon = {

                                Icon(
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(40.dp),
                                    painter = painterResource(id = item.selectedIcon),
                                    contentDescription = item.title
                                )
                            },
                            selectedContentColor = Color(0xff0d253f),
                            unselectedContentColor = Color(0xff0d253f)
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { index ->

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (selectedTabIndex) {
                            0 -> {
                                NowPlayingList(moviePagingItems)
                            }

                            1 -> {
                                PopularList(popularPagingItems)
                            }

                            2 -> {
                                UpComingList(upcomingPagingItems)
                            }


                        }

                    }

                }
            }
        }
    }
}




