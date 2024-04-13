package com.towich.kinopoiskDev.ui.screen.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.towich.kinopoiskDev.R
import com.towich.kinopoiskDev.data.model.MovieModel
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomHorizontalPager(
    pagerMovies: List<MovieModel>
) {
    val pagerState = rememberPagerState(pageCount = { pagerMovies.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.padding(top = 10.dp),
        contentPadding = PaddingValues(start = 32.dp, end = 64.dp),
        pageSpacing = 32.dp
    ) { page ->
        val currMovie = pagerMovies[page]

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            SubcomposeAsyncImage(
                model = currMovie.posterPreviewUrl,
                contentDescription = "Poster",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 450.dp)
                    .shadow(
                        elevation = 15.dp,
                        shape = RoundedCornerShape(10)
                    )
                    .clip(shape = RoundedCornerShape(10)),
                loading = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
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
                            .height(450.dp)
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
            Text(
                text = currMovie.name ?: stringResource(id = R.string.null_field),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 20.dp)
            )

            val genres = StringBuilder(currMovie.genres[0])
            for (i in 1 until currMovie.genres.size) {
                genres.append(", ${currMovie.genres[i]}")
            }

            Text(
                text = genres.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}