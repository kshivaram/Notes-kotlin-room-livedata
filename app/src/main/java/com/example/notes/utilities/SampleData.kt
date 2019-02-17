package com.example.notes.utilities

import com.example.notes.database.NoteEntity
import java.util.*
import kotlin.collections.ArrayList

object SampleData {
    fun getDate(diff: Int): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MILLISECOND, diff)
        return cal.time
    }

    fun getNotes(): List<NoteEntity> {

        val notesList = ArrayList<NoteEntity>()
        notesList.add(NoteEntity(id = 1, text = "First Note"))
        notesList.add(NoteEntity(id = 2, text = "Second Note"))
        notesList.add(NoteEntity(id = 3, text =  "Third Note"))

        return notesList
    }

}