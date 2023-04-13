package com.rbodai.icellswapi.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rbodai.icellswapi.R
import com.rbodai.icellswapi.presentation.views.MainScreen
import com.rbodai.icellswapi.ui.theme.SWAPITheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.*

@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    companion object {
      init {
         System.loadLibrary("g3d")
      }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SWAPITheme {
                MainScreen()
            }
        }
        writeToFile("tie.obj", readFile())
    }

    fun readFile() : String {
        var content: String? = ""
        val stringBuilder = StringBuilder()
        val `is`: InputStream = this.resources.openRawResource(R.raw.tie)
        val reader = BufferedReader(InputStreamReader(`is`))
        while (true) {
            try {
                if (reader.readLine().also { content = it } == null) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            stringBuilder.append(content).append("\n")
        }
        `is`.close()
        return stringBuilder.toString()
    }

    fun writeToFile(fileName: String, content: String) {
        val path = applicationContext.getExternalFilesDir(null)
        try {
            val writer = FileOutputStream(File(path, fileName))
            writer.write(content.toByteArray())
            writer.close()
        } catch (e: java.lang.Exception) {
            Log.i("G3D", "Error during file operation: " + e.message)
        }
    }
}

