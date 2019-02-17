package com.example.notes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.room.Room


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NoteDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context) :AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "note_database"
                ).build();
            }
        }
    }
}