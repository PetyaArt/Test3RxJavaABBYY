// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.test2rxjavaabbyy.ui.presentation

import android.os.Environment
import android.util.Log
import com.test2rxjavaabbyy.CodeFileObserver
import com.test2rxjavaabbyy.version.ObservableFile
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.File

class CatalogPresenter(val view: CatalogView) {

    private val disposables = CompositeDisposable()

    fun bind() {
        val file = Environment.getExternalStoragePublicDirectory("Photo")

        val event = Observable.create(ObservableFile(file.absolutePath))
            .map { data ->
                return@map when (data.code) {
                    CodeFileObserver.CREATE -> CatalogStatePartialViewStates.addImage(data.name)
                    CodeFileObserver.DELETE -> CatalogStatePartialViewStates.deleteImage(data.name)
                }
            }


        disposables.add(event
            .scan(CatalogState.DataState(getListFile(file))) { previousViewState, partialViewState ->
                partialViewState.invoke(previousViewState)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewState ->
                view.render(viewState)
            })
    }

    fun getListFile(file: File): ArrayList<String> {
        val list = arrayListOf<String>()
        for (item in file.listFiles()) {
            list.add(item.name)
        }
        return list
    }

    fun unbind() = disposables.clear()

}