package com.example.myapplication3.view.ui.activities.home.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.commandiron.compose_loading.Pulse
import com.example.myapplication3.R
import com.example.myapplication3.domain.entities.Result
import com.example.myapplication3.view.ui.activities.home.HomeDetailsScreen

@Composable
fun PopularList(popularPagingItem: LazyPagingItems<Result>) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow {
            items(popularPagingItem.itemCount) { index ->
                Card(
                    onClick = {
                        val i: Intent = Intent(
                            context,
                            HomeDetailsScreen::class.java
                        )
                        i.putExtra(
                            "name",
                            popularPagingItem[index]!!.title
                        )
                        i.putExtra(
                            "description",
                           popularPagingItem[index]!!.overview
                        )
                        i.putExtra(
                            "votes",
                            popularPagingItem[index]!!.voteAverage
                        )
                        i.putExtra(
                            "image",
                            popularPagingItem[index]!!.posterPath
                        )
                        (context as Activity).startActivity(i)
                    },
                    // in the below line, we are adding
                    // padding from our all sides.
                    modifier = Modifier
                        .padding(vertical = 40.dp, horizontal = 7.dp)
                        .width(240.dp)
                        .fillMaxHeight(),

                    // in the below line, we are adding
                    // elevation for the card.
                    elevation = CardDefaults.elevatedCardElevation(
                        disabledElevation = 6.dp
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/w500" + popularPagingItem[index]!!.posterPath!!)
                                .crossfade(true)
                                .scale(scale = Scale.FIT)
                                .build(),
                            contentDescription = "1213",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth()
                                .height(300.dp),
                        )

                        Text(
                            text = popularPagingItem[index]!!.title!!,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 9.dp)
                                .padding(horizontal = 8.dp),
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color(0xff0d253f),
                        )
                        Spacer(
                            Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(Color.Transparent)
                        )

                        Row {
                            Text(
                                text = "Release Date:",
                                modifier = Modifier
                                    .padding(
                                        horizontal = 12.dp,
                                        vertical = 8.dp
                                    ),
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = popularPagingItem[index]!!.releaseDate!!,
                                modifier = Modifier
                                    .padding(
                                        vertical = 8.dp
                                    ),
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }

            }
           popularPagingItem.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Pulse(
                                size = DpSize(
                                    width = 50.dp,
                                    height = 50.dp
                                ),
                                color = Color(0xff0d253f)
                            )
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }
            }
        }


    }
}