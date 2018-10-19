package com.raywenderlich.android.datadrop.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raywenderlich.android.datadrop.app.DataDropApplication
import java.io.*
import java.lang.StringBuilder

object FileRepository : DropRepository {

    private val gson: Gson
        get() {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(Drop::class.java, DropTypeAdapter())
            return builder.create()
        }

    private fun getContext() = DataDropApplication.getAppContext()

    private fun dropsDirectory() = getContext().getDir("drops", Context.MODE_PRIVATE)

    private fun dropFile(filename: String) = File(dropsDirectory(), filename)

    private fun dropFilename(drop: Drop) = drop.id + ".drop"

    private fun dropOutPutStream(drop: Drop): FileOutputStream {
        return FileOutputStream(dropFile(dropFilename(drop)))
    }

    private fun dropInPutStream(filename: String): FileInputStream {
        return FileInputStream(dropFile(filename))
    }

    override fun addDrop(drop: Drop) {
        val string = gson.toJson(drop)
        try {
            val dropStream = dropOutPutStream(drop)
            dropStream.write(string.toByteArray())
            dropStream.close()
        } catch (e: IOException) {
            Log.e("FileRepository", "Error saving drop")
        }

    }

    override fun getDrops(): List<Drop> {
        val drops = mutableListOf<Drop>()


        try {
            val fileList = dropsDirectory().list()
            fileList.map { convertStreamtoString(dropInPutStream(it)) }.mapTo(drops) {
                gson.fromJson(it, Drop::class.java)
            }
        } catch (e: IOException) {
            Log.e("FileRepository", "Error saving drop")
        }

        return drops
    }

    override fun clearDrop(drop: Drop) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllDrops() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun convertStreamtoString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        try {
            var line: String? = reader.readLine()
            while (line != null) {
                sb.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: IOException) {
            Log.e("FileRepository", "Error saving drop")
        }

        return sb.toString()
    }
}