package com.towich.kinopoiskDev.ui.screen.allmovies

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.towich.kinopoiskDev.R
import com.towich.kinopoiskDev.data.room.entity.QueryEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllMoviesScreen(
    viewModel: AllMoviesViewModel,
    onMovieClicked: () -> Unit,
    onFilterIconClicked: () -> Unit,
    onNavIconClicked: () -> Unit
) {
    val movies = viewModel.getMovies().collectAsLazyPagingItems()
    val searchQuery by viewModel.search.collectAsState()
    val searchedMovies = viewModel.searchedMovies.collectAsLazyPagingItems()
    val showSearchBar by viewModel.isSearchShowing.collectAsState()
    val queries by viewModel.queries.collectAsState()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (showSearchBar) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        var active by remember { mutableStateOf(false) }

                        DockedSearchBar(
                            query = searchQuery,
                            onQueryChange = { newSearchQuery ->
                                viewModel.setSearch(query = newSearchQuery)
                            },
                            onSearch = { newQuery ->
                                println("Performing search on query: $newQuery")
                                if (searchQuery.replace("\\s".toRegex(), "").isNotEmpty()) {
                                    viewModel.insertQuery(QueryEntity(id = null, query = searchQuery))
                                }
                                active = false
                            },
                            active = active,
                            onActiveChange = { active = it },
                            placeholder = {
                                Text(text = stringResource(R.string.search))
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = stringResource(R.string.search)
                                )
                            },
                            trailingIcon = {
                                Row {
                                    if (active) {
                                        IconButton(
                                            onClick = {
                                                viewModel.setSearch("")
                                                if (searchQuery.isEmpty()) {
                                                    active = false
                                                    viewModel.toggleIsSearchShowing()
                                                } else {
                                                    if (searchQuery.replace("\\s".toRegex(), "").isNotEmpty()) {
                                                        viewModel.insertQuery(QueryEntity(id = null, query = searchQuery))
                                                    }
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Close,
                                                contentDescription = "Close"
                                            )
                                        }
                                    }
                                }
                            },
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 240.dp)

                            ) {
                                items(queries.reversed().takeLast(20)) { item ->
                                    ListItem(
                                        modifier = Modifier.clickable { viewModel.setSearch(item.query.toString()) },
                                        headlineContent = { Text(text = item.query.toString()) },
                                        leadingContent = {
                                            Icon(
                                                painterResource(id = R.drawable.history_icon),
                                                contentDescription = null
                                            )
                                        }
                                    )
                                }
                            }
                        }
                        BackHandler {
                            viewModel.insertQuery(QueryEntity(id = null, query = searchQuery))
                            viewModel.toggleIsSearchShowing()
                            viewModel.setSearch("")
                        }
                    }

                } else {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = stringResource(id = R.string.all_movies_screen_title),
                                fontWeight = FontWeight.Bold
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { onNavIconClicked() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = "All films",
                                    modifier = Modifier.size(36.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { onFilterIconClicked() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.filter_icon),
                                    contentDescription = "Filters",
                                    modifier = Modifier.size(28.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            IconButton(onClick = { viewModel.toggleIsSearchShowing() }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.size(28.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        }
    ) { scaffoldPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(all = 20.dp),
            verticalItemSpacing = 20.dp,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(if (searchQuery != "") searchedMovies.itemCount else movies.itemCount) { index ->
                Column(
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    SubcomposeAsyncImage(
                        model = (if (searchQuery != "") searchedMovies else movies)[index]?.posterPreviewUrl,
                        contentDescription = "Poster",
                        contentScale = ContentScale.FillWidth,
                        loading = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
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
                                    .height(250.dp)
                                    .background(color = MaterialTheme.colorScheme.surface)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.no_photo),
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .heightIn(max = 250.dp)
                            .shadow(
                                elevation = 15.dp,
                                shape = RoundedCornerShape(10)
                            )
                            .clip(shape = RoundedCornerShape(10))
                            .clickable {
                                viewModel.performSetCurrentMovie((if (searchQuery != "") searchedMovies else movies)[index]!!)
                                onMovieClicked()
                            }
                    )

                    Text(
                        text = (if (searchQuery != "") searchedMovies else movies)[index]?.name
                            ?: stringResource(id = R.string.no_info_name),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            item {
                when (movies.loadState.append) { // Pagination
                    is LoadState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ((if (searchQuery != "") searchedMovies else movies).loadState.append as LoadState.Error).error.message
                                    ?: stringResource(
                                        id = R.string.error
                                    ),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    is LoadState.Loading -> { // Pagination Loading UI
                        Column(
                            modifier = Modifier
                                .padding(top = 64.dp, bottom = 200.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(id = R.string.loading),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }

                    }

                    else -> {}
                }
            }
        }

        when ((if (searchQuery != "") searchedMovies else movies).loadState.refresh) { // FIRST LOAD

            is LoadState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = ((if (searchQuery != "") searchedMovies else movies).loadState.refresh as LoadState.Error).error.message
                            ?: stringResource(id = R.string.error),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            is LoadState.Loading -> { // Loading UI
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = stringResource(id = R.string.loading),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }

            }

            else -> {}
        }
    }
}