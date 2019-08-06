// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.test2rxjavaabbyy.ui.presentation

sealed class CatalogState {
    object LoadingState : CatalogState()
    data class DataState(val data: ArrayList<String>) : CatalogState()
    object ErrorState : CatalogState()
}