package com.example.bubblesortvisualizer.domain.usecase

import com.example.bubblesortvisualizer.domain.model.SortData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BubbleSort {
    operator fun invoke(list: MutableList<Int>): Flow<SortData> = flow {
        var listSizeToCompare = list.size - 1

        while (listSizeToCompare > 1) {
            var innerIterator = 0;
            while (innerIterator < listSizeToCompare) {
                val curListItem = list[innerIterator]
                val nextListItem = list[innerIterator + 1]
                emit(
                    SortData(curItem = innerIterator, swap = false, doNothing = false)
                )
                delay(500)
                if (curListItem > nextListItem) {
                    list.swap(innerIterator, innerIterator + 1)
                    emit(
                        SortData(curItem = innerIterator, swap = true, doNothing = false)
                    )
                } else {
                    emit(
                        SortData(curItem = innerIterator, swap = false, doNothing = true)
                    )
                }
                delay(500)
                innerIterator += 1;
            }
            listSizeToCompare -= 1;
        }
    }
}

fun <T> MutableList<T>.swap(indexOne: Int, indexTwo: Int) {
    val temp = this[indexOne]
    this[indexOne] = this[indexTwo]
    this[indexTwo] = temp
}