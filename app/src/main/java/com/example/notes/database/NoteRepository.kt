package com.example.notes.database

import com.example.notes.utilities.SampleData

class NoteRepository {

    fun getNotes() = SampleData.getNotes()

    companion object {
        @Volatile private var instance: NoteRepository? = null

        fun getInstance() :NoteRepository{
            instance ?: synchronized(this) {
                instance ?: NoteRepository()
            }
            return instance!!
        }
    }
}