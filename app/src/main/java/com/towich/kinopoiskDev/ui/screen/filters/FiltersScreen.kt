package com.towich.kinopoiskDev.ui.screen.filters

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.towich.kinopoiskDev.R
import com.towich.kinopoiskDev.data.model.ChipModel
import com.towich.kinopoiskDev.ui.screen.filters.components.ApplyButton
import com.towich.kinopoiskDev.ui.screen.filters.components.FilterComponent
import com.towich.kinopoiskDev.ui.util.ScreenUiState
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersScreen(
    appContext: Context,
    viewModel: FiltersViewModel,
    onNavIconClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val listOfChosenFilters = viewModel.performGetFilters()

    val genres by viewModel.genres.collectAsState()
    val countries by viewModel.countries.collectAsState()

    var chosenGenre by remember {
        mutableStateOf(
            if (listOfChosenFilters[0] != null)
                listOfChosenFilters[0]!!
            else
                "All"
        )
    }
    var chosenCountry by remember {
        mutableStateOf(
            if (listOfChosenFilters[1] != null)
                listOfChosenFilters[1]!!
            else
                "All"
        )
    }

    var chosenYear by remember {
        mutableStateOf(
            if (listOfChosenFilters[2] != null)
                listOfChosenFilters[2]!!
            else
                ""
        )
    }

    var chosenAge by remember {
        mutableStateOf(
            if (listOfChosenFilters[3] != null) {
                val rangeList = listOfChosenFilters [3]!!.split("-")
                rangeList[0].toFloat()..rangeList[1].toFloat()
            }
            else
                0f..18f
        )
    }

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
                    IconButton(
                        onClick = {
                            chosenGenre = "All"
                            chosenCountry = "All"
                            chosenYear = ""
                            chosenAge = 0f..18f

                            Toast.makeText(
                                appContext,
                                appContext.getString(R.string.cleared),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clear_filter),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
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
                    chosenChip = ChipModel(chosenGenre),
                    isFiltersShowsOnStart = true,
                    isLoading = uiState is ScreenUiState.Loading,
                    onChipClick = { chip ->
                        chosenGenre = chip.name
                    }
                )

                // Country
                FilterComponent(
                    title = stringResource(id = R.string.filters_screen_country),
                    listOfChips = countries.map { it.convertToChipModel() },
                    modifier = Modifier
                        .padding(top = 20.dp),
                    chosenChip = ChipModel(chosenCountry),
                    isFiltersShowsOnStart = false,
                    isLoading = uiState is ScreenUiState.Loading,
                    onChipClick = { chip ->
                        chosenCountry = chip.name
                    }
                )

                // Year
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.filters_screen_year),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = chosenYear,
                        onValueChange = { newYear ->
                            if (newYear.length <= 4)
                                chosenYear = newYear
                        },
                        shape = CircleShape,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                    )

                }

                // Age rating
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.filters_screen_age_rating),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "${chosenAge.start.roundToInt()} - ${chosenAge.endInclusive.roundToInt()}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.End
                    )
                }

                RangeSlider(
                    value = chosenAge,
                    steps = 5,
                    onValueChange = { range -> chosenAge = range },
                    valueRange = 0f..18f,
                    onValueChangeFinished = { },
                )


                Spacer(modifier = Modifier.height(400.dp))

            }

            ApplyButton(
                text = stringResource(id = R.string.filters_screen_apply_button),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                if (chosenYear != "" && (chosenYear.toIntOrNull() == null || chosenYear.toInt() < 1874 || chosenYear.toInt() > 2050)) {
                    Toast.makeText(
                        appContext,
                        appContext.getString(R.string.year_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    chosenYear = ""
                } else {
                    val listOfFilters: List<String?> = listOf(
                        chosenGenre,
                        chosenCountry,
                        chosenYear,
                        "${chosenAge.start.roundToInt()}-${chosenAge.endInclusive.roundToInt()}"
                    ).map { if (it == "All" || it == "") null else it }

                    viewModel.applyFilters(listOfFilters)

                    onNavIconClicked()
                }
            }
        }
    }
}