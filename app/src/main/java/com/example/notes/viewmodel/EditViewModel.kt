package com.example.notes.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.example.notes.database.AppDatabase
import com.example.notes.database.NoteEntity
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class EditViewModel(application: Application): AndroidViewModel(application) {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val mDatabase: AppDatabase = AppDatabase.getInstance(application)
    val mLiveNote =  MutableLiveData<NoteEntity>()

    fun getNoteById(key: Int) {
        executor.execute {
            val noteEntity = mDatabase.notesDao().loadNotesByKey(key)
            mLiveNote.postValue(noteEntity)
        }
    }

    fun saveNote(noteText: String) {
        var noteEntity = mLiveNote.value

        if (noteEntity != null) {
            noteEntity.text = noteText.trim()

        } else {
            if (TextUtils.isEmpty(noteText.trim()))
                return
            noteEntity = NoteEntity(id = 7, text = noteText.trim())
        }
        executor.execute {
            mDatabase.notesDao().insertNote(noteEntity)
        }
    }

    fun deleteNote() {
        val noteEntity = mLiveNote.value
        if (noteEntity != null) {
            executor.execute {
                mDatabase.notesDao().deleteNote(noteEntity)
            }
        }
    }
}