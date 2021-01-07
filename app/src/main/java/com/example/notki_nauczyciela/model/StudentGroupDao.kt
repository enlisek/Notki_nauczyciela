package com.example.notki_nauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentGroupDao {
    @Insert
    suspend fun insert(studentGroup:StudentGroup)

    @Delete
    suspend fun delete(studentGroup:StudentGroup)

    @Query("DELETE FROM student_group_table WHERE group_id=:n AND student_id=:m")
    suspend fun deleteStudentFromGroup(n: Int,m: Int)



}