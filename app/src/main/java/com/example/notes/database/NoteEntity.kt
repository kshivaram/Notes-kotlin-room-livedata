package com.example.notes.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "notekey")  var key: Int = 0,
    @ColumnInfo(name = "noteid") var id : Int,
    @ColumnInfo(name = "notetext") var text : String)


