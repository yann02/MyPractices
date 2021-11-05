package com.hnsh.mystorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val path = filesDir.absolutePath
        Log.d("wyy", "path:$path")
        val file = File(path + File.separator + "myFile")
//        if (file.isDirectory) {
//            Log.d("wyy", "File is a directory.")
//            Log.d("wyy", "File has ${file.list()?.size ?: 0} files.")
//        val fileName = "myFile"
//            val fileContent = "我是好人，不要打我"
//            openFileOutput(fileName, Context.MODE_PRIVATE).bufferedWriter(Charsets.UTF_8).use {
//                it.write(fileContent)
//            }
//        }
//        openFileInput(fileName).bufferedReader().useLines {
//            it.fold("1") { some, text ->
//                Log.d("wyy","some:$some")
//                Log.d("wyy","text:$text")
//                "$some\n$text"
//            }
//            Log.d("wyy", "it:${it}")
//        }
        resources.openRawResource(R.raw.abc).bufferedReader().use {
            it.forEachLine { s ->
                Log.d("wyy", "it:$s")
            }
        }
        val path2 = externalCacheDir?.absolutePath
        Log.d("wyy", "path2:$path2")
    }
}