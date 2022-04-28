package com.agombiproducts.notes.database

import android.content.Context
import android.util.Log
import com.agombiproducts.notes.models.Note
import com.google.gson.Gson
import java.io.*
import java.util.stream.Collectors


class DatabaseToFile {
    var TAG: String = "TODO-FILE-WRITER"

    private fun initWriter(context: Context): File? {
        try {

            var directory: File = File(context.filesDir, "Database")
            if (!directory.exists()) {
                directory.mkdirs()
            }

            var file: File = File(directory, "todo.txt")
            if (!file.exists()) {
                file.createNewFile()
            }

            return file

        } catch (e: Exception) {
            Log.i(
                TAG,
                "something went wrong with init writer ${FormattedDate.getCurrentDate()}"
            )

        }
        return null
    }

    fun writer(context: Context, note: Note): Boolean {
        var file = initWriter(context)
        if (file != null) {
            try {
                var gson = Gson()
                var resultTodo = gson.toJson(note)
                var writer: BufferedWriter = BufferedWriter(FileWriter(file, true))
                writer.append(resultTodo)
                writer.newLine()
                writer.close()
                return true

            } catch (exception: Exception) {
                Log.i(
                    TAG,
                    "something went wrong in file writer ${FormattedDate.getCurrentDate()}"
                )

            }
        }
        return false
    }

    fun reader(context: Context): ArrayList<Note> {
        var file = initWriter(context)
        var result: ArrayList<Note> = ArrayList()
        if (file != null) {
            try {
                var reader: BufferedReader = BufferedReader(FileReader(file))
                var lines = reader.lines().collect(Collectors.toList())
                var gson = Gson()
                for (line in lines) {
                    var todo = gson.fromJson(line, Note::class.java)
                    result.add(todo)
                }
                return result

            } catch (exception: Exception) {
                Log.i(
                    TAG,
                    "something went wrong in file reader ${FormattedDate.getCurrentDate()}"
                )
            }
        }
        return ArrayList()
    }
}
