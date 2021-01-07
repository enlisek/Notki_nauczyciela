package com.example.notki_nauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student:Student)

    @Delete
    suspend fun delete(student:Student)

    @Query("SELECT * FROM student_table")
    fun allStudent(): LiveData<List<Student>>

    @Query("SELECT s.* FROM student_table s, student_group_table sg WHERE sg.group_id=:n AND s.id=sg.student_id")
    fun studentsInGroup(n: Int): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id NOT IN (SELECT student_id FROM student_group_table WHERE group_id=:n)")
    fun studentsOutGroup(n:Int): LiveData<List<Student>>
}