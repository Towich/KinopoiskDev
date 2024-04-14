package com.towich.kinopoiskDev.ui.screen.movie.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.towich.kinopoiskDev.R
import com.towich.kinopoiskDev.data.model.PosterModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AwesomeCarousel(
    posters: List<PosterModel>,
    pageCount: Int = 10,
    pagerState: PagerState = rememberPagerState(pageCount = { pageCount }),
    autoScrollDuration: Long = 3000L
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember { mutableStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = autoScrollDuration)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    HorizontalPager(state = pagerState) { page ->
        SubcomposeAsyncImage(
            model = posters[page].url,
            contentDescription = "Poster",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(600.dp)
                .shadow(
                    elevation = 15.dp,
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                ),
            loading = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 5.dp,
                        modifier = Modifier
                            .size(50.dp),
                    )
                }
            },
            error = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = stringResource(id = R.string.no_photo),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }

    DotIndicators(
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)

    )
}