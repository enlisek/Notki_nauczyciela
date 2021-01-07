package com.example.notki_nauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroupDao {
    @Insert
    suspend fun insert(group: Group)

    @Delete
    suspend fun delete(group: Group)

    @Query("SELECT * FROM group_table")
    fun allGroups(): LiveData<List<Group>>

    @Query("SELECT * FROM group_table WHERE id=:n ")
    fun groupN(n:Int): LiveData<Group>
}