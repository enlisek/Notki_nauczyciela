package com.example.notki_nauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MarkDao {
    @Insert
    suspend fun insert(mark: Mark)

    @Delete
    suspend fun delete(mark: Mark)

    @Query("Select * from marks_table")
    fun allMarks(): LiveData<List<Mark>>

    @Query("SELECT * FROM marks_table WHERE group_id=:n AND student_id=:m ")
    fun marksOfStudent(n: Int, m:Int): LiveData<List<Mark>>

    @Query("SELECT * FROM marks_table WHERE date=:today ")
    fun marksToday(today:String): LiveData<List<Mark>>

}