// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.test2rxjavaabbyy.version

import android.os.FileObserver
import android.util.Log
import com.test2rxjavaabbyy.CodeFileObserver
import com.test2rxjavaabbyy.Data
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import java.io.File

class ObservableFile(val path: String) : ObservableOnSubscribe<Data> {

    override fun subscribe(emitter: ObservableEmitter<Data>) {
        val fileObserver = object: FileObserver(path, ALL_EVENTS) {
            override fun onEvent(code: Int, message: String?) {
                when(code) {
                    CREATE -> emitter.onNext(Data(CodeFileObserver.DELETE, message!!))
                    DELETE -> emitter.onNext(Data(CodeFileObserver.DELETE, message!!))
                }
            }
        }
        fileObserver.startWatching()
        emitter.setDisposable( Disposables.fromAction {
            fileObserver.stopWatching()
        })
    }
}