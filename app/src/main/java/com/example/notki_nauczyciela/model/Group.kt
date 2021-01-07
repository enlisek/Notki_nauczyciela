package com.example.notki_nauczyciela.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class Group(@PrimaryKey(autoGenerate = true) val id:Int, var name:String="")