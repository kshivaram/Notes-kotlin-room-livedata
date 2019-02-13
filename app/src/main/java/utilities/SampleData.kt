package utilities

import database.NoteEntity
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
        notesList.add(NoteEntity(1, "First Note"))
        notesList.add(NoteEntity(2, "Second Note"))
        notesList.add(NoteEntity(3,  "Third Note"))

        return notesList
    }

}