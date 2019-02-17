package com.example.notes

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.example.notes.database.AppDatabase
import com.example.notes.database.NoteDAO
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.notes.utilities.SampleData
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    val TAG :String = "JUNIT"
    private var mdatabase: AppDatabase? = null
    private var noteDAO: NoteDAO? = null

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        mdatabase = Room.inMemoryDatabaseBuilder(context,
                    AppDatabase::class.java).build()
        noteDAO = mdatabase!!.notesDao()
        Log.e(TAG, "Created com.example.notes.database")
    }

    @After
    fun closeDb(){
        mdatabase!!.close()
        Log.e(TAG, "Closed com.example.notes.database")

    }
    @Test
    fun createAndRetrieveNotes() {
        noteDAO!!.insertAll(SampleData.getNotes())
        val count = noteDAO!!.getCount()
        Log.e(TAG, count.toString())
        assertEquals(SampleData.getNotes().size, count)

    }
}