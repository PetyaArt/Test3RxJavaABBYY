package com.test2rxjavaabbyy.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test2rxjavaabbyy.ui.presentation.CatalogState
import com.test2rxjavaabbyy.R
import com.test2rxjavaabbyy.ui.presentation.CatalogPresenter
import com.test2rxjavaabbyy.ui.presentation.CatalogView

class CatalogActivity : AppCompatActivity(), CatalogView {

    private val presenter = CatalogPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind()
    }

    override fun render(state: CatalogState) {
        when(state) {
            is CatalogState.DataState -> {
                state.data.sortBy { it }
                for (item in state.data) {
                    Log.d("myLogs", item)
                }
            }
            is CatalogState.ErrorState -> {}
            is CatalogState.LoadingState -> {}
        }
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}
