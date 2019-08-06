// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.test2rxjavaabbyy.ui.presentation

private typealias CatalogStatePartialViewState = (CatalogState.DataState) -> CatalogState.DataState

object CatalogStatePartialViewStates {

    fun addImage(image: String) : CatalogStatePartialViewState = { previousViewState ->
        previousViewState.copy(
            data = previousViewState.data.plus(image) as ArrayList<String>
        )
    }

    fun deleteImage(image: String) : CatalogStatePartialViewState = { previousViewState ->
        previousViewState.copy(
            data = previousViewState.data.minus(image) as ArrayList<String>
        )
    }
}