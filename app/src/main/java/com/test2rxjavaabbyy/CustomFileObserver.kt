// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.test2rxjavaabbyy

import android.os.FileObserver
import io.reactivex.subjects.PublishSubject

class CustomFileObserver(path: String) : FileObserver(path, ALL_EVENTS) {

    private val subject = PublishSubject.create<Int>()

    val observable = subject
        .doOnSubscribe { startWatching() }
        .doOnDispose { stopWatching() }
        .share()

    override fun onEvent(code: Int, message: String?) {
        when(code) {
            DELETE -> subject.onNext(code)
            CREATE -> subject.onNext(code)
        }
    }
}