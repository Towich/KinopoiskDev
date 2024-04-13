package com.towich.kinopoiskDev.ui.screen.filters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.towich.kinopoiskDev.data.model.ChipModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterComponent(
    title: String,
    listOfChips: List<ChipModel>,
    chosenChip: ChipModel,
    isFiltersShowsOnStart: Boolean = false,
    isLoading: Boolean,
    onChipClick: (chip: ChipModel) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFiltersShows by remember { mutableStateOf(isFiltersShowsOnStart) }

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(onClick = { isFiltersShows = !isFiltersShows }) {
                Icon(
                    imageVector = if (isFiltersShows) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Show filters",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (isFiltersShows) {
            FlowRow(
                verticalArrangement = Arrangement.Center
            ) {
                FilterChip(
                    title = "All",
                    isSelected = chosenChip.name == "All",
                    modifier = Modifier
                        .padding(top = 10.dp, end = 15.dp),
                    onClick = {
                        onChipClick(ChipModel("All"))
                    }
                )

                if(isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .size(32.dp)
                    )
                }
                else {
                    for (chip in listOfChips) {
                        FilterChip(
                            title = chip.name,
                            isSelected = chip.name == chosenChip.name,
                            modifier = Modifier.padding(top = 10.dp, end = 15.dp),
                            onClick = {
                                onChipClick(chip)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterChip(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.surface

    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(25.dp)
            )
            .clip(RoundedCornerShape(25.dp))
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        )
    }
}