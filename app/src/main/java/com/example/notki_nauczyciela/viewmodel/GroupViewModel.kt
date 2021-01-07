package com.example.notki_nauczyciela.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.MyDatabase
import com.example.notki_nauczyciela.model.Student
import com.example.notki_nauczyciela.model.repositories.GroupRepository
import kotlinx.coroutines.launch

class GroupViewModel(application: Application): AndroidViewModel(application)  {

    private val groupRepository: GroupRepository

    lateinit var listOfGroups: LiveData<List<Group>>

    init {
        groupRepository = GroupRepository(MyDatabase.getDatabase(application).groupDao())
        updateGroups()
    }


    fun updateGroups()
    {
        listOfGroups=groupRepository.allGroups()
    }


    fun addGroup(nameOfGroup:String)
    {
        viewModelScope.launch {
            groupRepository.add(Group(name = nameOfGroup,id = 0))
        }
    }

    fun deleteGroup(group: Group)
    {
        viewModelScope.launch {
            groupRepository.delete(group)
        }

    }
}