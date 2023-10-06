package com.example.bubblesortvisualizer.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubblesortvisualizer.domain.usecase.BubbleSort
import com.example.bubblesortvisualizer.presentation.state.ListUiItem
import kotlinx.coroutines.launch

class SortViewModel(
    private val bubbleSort: BubbleSort = BubbleSort()
) : ViewModel() {
    var listToSort = mutableStateListOf<ListUiItem>()

    init {
        for (i in 0 until 9) {
            listToSort.add(
                ListUiItem(
                    id = i,
                    isCurrentlyCompared = false,
                    value = (0..150).random(),
                    color = Color(
                        245,
                        (0..130).random(),
                        (0..50).random(),
                        255
                    )
                )
            )
        }
    }

    fun startSorting() {
        viewModelScope.launch {
            bubbleSort(listToSort.map { listUiItem ->
                listUiItem.value
            }.toMutableList()).collect { sortData ->
                val curItemIndex = sortData.curItem
                listToSort[curItemIndex] = listToSort[curItemIndex].copy(isCurrentlyCompared = true)
                listToSort[curItemIndex + 1] =
                    listToSort[curItemIndex + 1].copy(isCurrentlyCompared = true)

                if (sortData.swap) {
                    val firstItem = listToSort[curItemIndex].copy(isCurrentlyCompared = false)
                    listToSort[curItemIndex] =
                        listToSort[curItemIndex + 1].copy(isCurrentlyCompared = false)
                    listToSort[curItemIndex + 1] = firstItem
                }
                if (sortData.doNothing) {
                    listToSort[curItemIndex] =
                        listToSort[curItemIndex].copy(isCurrentlyCompared = false)
                    listToSort[curItemIndex + 1] =
                        listToSort[curItemIndex + 1].copy(isCurrentlyCompared = false)
                }
            }
        }
    }
}