package database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDAO {

    @Insert
    fun insertNote(noteEntity: NoteEntity)

    @Insert
    fun insertAll(noteEntityList: List<NoteEntity>)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id LIKE :noteId")
    fun loadNotesById(noteId: Int) : NoteEntity

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun loadAllNotes(): LiveData<NoteEntity>

    @Query("DELETE FROM notes")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM notes")
    fun getCount(): Int

}