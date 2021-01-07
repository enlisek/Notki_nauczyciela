package com.example.notki_nauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.GroupDao

class GroupRepository(private val groupDao: GroupDao) {


    suspend fun add(group: Group) {
        groupDao.insert(group)
    }

    fun allGroups(): LiveData<List<Group>>
    {
        return groupDao.allGroups()
    }

    suspend fun delete(group: Group)=groupDao.delete(group)

}