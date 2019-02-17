package com.example.notes.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(noteEntityList: List<NoteEntity>)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE notekey LIKE :noteKey")
    fun loadNotesByKey(noteKey: Int) : NoteEntity

    @Query("SELECT * FROM notes ORDER BY notekey DESC")
    fun loadAllNotes(): LiveData<List<NoteEntity>>

    @Query("DELETE FROM notes")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM notes")
    fun getCount(): Int

}