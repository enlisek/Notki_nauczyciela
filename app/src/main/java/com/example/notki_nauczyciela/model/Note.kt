package com.example.notki_nauczyciela.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = true) val id:Int, var note:String="")