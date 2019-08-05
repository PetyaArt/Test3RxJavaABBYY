package com.test2rxjavaabbyy

import android.os.Bundle
import android.os.Environment
import android.os.FileObserver
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileObserver = CustomFileObserver(Environment.getExternalStoragePublicDirectory("Photo").absolutePath)
        disposable.add(fileObserver.observable
            .subscribe {code->
                when(code) {
                    FileObserver.CREATE -> {
                        Log.d("myLogs", "CREATE")
                    }
                    FileObserver.DELETE -> {
                        Log.d("myLogs", "DELETE")
                    }
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
