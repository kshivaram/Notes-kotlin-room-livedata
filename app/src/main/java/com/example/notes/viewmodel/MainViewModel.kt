package com.example.notes.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.notes.database.AppDatabase
import com.example.notes.database.NoteEntity
import com.example.notes.utilities.SampleData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainViewModel(application: Application) : AndroidViewModel(application) {

    //val mNotes = MutableLiveData<List<NoteEntity>>()
    private var mDatabase: AppDatabase? = AppDatabase.getInstance(application.applicationContext)

    private val executor: Executor = Executors.newSingleThreadExecutor()

    fun addSampleData() {
        executor.execute {
            run {
                mDatabase!!.notesDao().insertAll(SampleData.getNotes())
            }
        }
    }

    fun deleteAllData() {
        executor.execute{
            run {
                mDatabase!!.notesDao().deleteAll()
            }
        }
    }

    fun getAllNotes() :LiveData<List<NoteEntity>>{
       /* executor.execute {
            run {
                val notes = mDatabase!!.notesDao().loadAllNotes()
                mNotes.postValue(notes)
            }
        }*/
        return mDatabase!!.notesDao().loadAllNotes()
    }
}