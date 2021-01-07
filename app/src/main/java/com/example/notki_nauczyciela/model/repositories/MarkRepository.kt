package com.example.notki_nauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.notki_nauczyciela.model.*

class MarkRepository(private val markDao: MarkDao) {

    suspend fun add(mark:Mark) {
        markDao.insert(mark)
    }

    suspend fun delete(mark:Mark)=markDao.delete(mark)

    fun allMarks(): LiveData<List<Mark>>
    {
        return markDao.allMarks()
    }

    fun marksOfStudent(n: Int, m:Int): LiveData<List<Mark>>
    {
        return markDao.marksOfStudent(n, m)
    }

    fun marksToday(today: String): LiveData<List<Mark>>
    {
        return markDao.marksToday(today)
    }

}