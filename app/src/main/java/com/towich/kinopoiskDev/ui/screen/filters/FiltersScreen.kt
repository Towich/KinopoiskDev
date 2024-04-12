package com.towich.kinopoiskDev.ui.screen.filters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.towich.kinopoiskDev.R
import com.towich.kinopoiskDev.data.model.ChipModel
import com.towich.kinopoiskDev.ui.screen.filters.components.ApplyButton
import com.towich.kinopoiskDev.ui.screen.filters.components.FilterComponent
import com.towich.kinopoiskDev.ui.util.ScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersScreen(
    viewModel: FiltersViewModel,
    onNavIconClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val genres by viewModel.genres.collectAsState()
    val countries by viewModel.countries.collectAsState()

    var chosenGenre by remember { mutableStateOf(ChipModel("All")) }
    var chosenCountry by remember { mutableStateOf(ChipModel("All")) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.filters_screen_top_bar_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                actions = {
                    IconButton(onClick = {
                        val chipNone = ChipModel("All")
                        chosenGenre = chipNone
                        chosenCountry = chipNone
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.clear_filter),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onNavIconClicked() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { topBarPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(topBarPadding)
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {

                // Genre
                FilterComponent(
                    title = stringResource(id = R.string.filters_screen_genre),
                    listOfChips = genres.map { it.convertToChipModel() },
                    modifier = Modifier
                        .padding(top = 20.dp),
                    chosenChip = chosenGenre,
                    isFiltersShowsDefault = true,
                    isLoading = uiState is ScreenUiState.Loading,
                    onChipClick = { chip ->
                        chosenGenre = chip
                    }
                )

                // Country
                FilterComponent(
                    title = stringResource(id = R.string.filters_screen_country),
                    listOfChips = countries.map { it.convertToChipModel() },
                    modifier = Modifier
                        .padding(top = 30.dp),
                    chosenChip = chosenCountry,
                    isLoading = uiState is ScreenUiState.Loading,
                    onChipClick = { chip ->
                        chosenCountry = chip
                    }
                )

                Spacer(modifier = Modifier.height(400.dp))

            }

            ApplyButton(
                text = stringResource(id = R.string.filters_screen_apply_button),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                val listOfFilters: List<String?> = listOf(
                    chosenGenre,
                    chosenCountry
                ).map { if (it.name != "All") it.name else null }

                viewModel.applyFilters(listOfFilters)

                onNavIconClicked()
            }
        }
    }
}