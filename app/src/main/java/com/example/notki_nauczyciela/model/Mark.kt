package com.example.notki_nauczyciela.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "marks_table",
        foreignKeys = [
            ForeignKey(
                    entity = Student::class,
                    parentColumns = ["id"],
                    childColumns = ["student_id"],
                    onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                    entity = Group::class,
                    parentColumns = ["id"],
                    childColumns = ["group_id"],
                    onDelete = ForeignKey.CASCADE
            )
        ]
)
class Mark(@PrimaryKey(autoGenerate = true) val id:Int,
           @ColumnInfo( index = true) val student_id:Int,
           @ColumnInfo( index = true) val group_id:Int,
           val value:String, val note:String, val date: String)