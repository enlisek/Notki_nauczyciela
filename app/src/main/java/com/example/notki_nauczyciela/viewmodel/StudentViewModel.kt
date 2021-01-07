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
import com.example.notki_nauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application)  {

//    lateinit var listOfIdStudentsInGroup: LiveData<List<Int>>
    lateinit var listOfStudentInGroup: LiveData<List<Student>>
    lateinit var listOfStudentOutGroup: LiveData<List<Student>>

    fun updateStudents() {
        listOfStudentInGroup = studentRepository.studentsInGroup(currentGroup.value!!.id)
        listOfStudentOutGroup = studentRepository.studentsOutGroup(currentGroup.value!!.id)
    }

    private var _currentGroup:MutableLiveData<Group> = MutableLiveData()
    val currentGroup:LiveData<Group>
        get()=_currentGroup

    private val studentRepository: StudentRepository
    private val groupRepository: GroupRepository
    init{
        studentRepository=StudentRepository(MyDatabase.getDatabase(application).studentDao(),MyDatabase.getDatabase(application).studentGroupDao())
        groupRepository= GroupRepository(MyDatabase.getDatabase(application).groupDao())

    }

    fun setCurrentGroup(group: Group)
    {
        _currentGroup.value=group
    }

    fun addStudent(firstName:String, lastName:String)
    {
        viewModelScope.launch {
            studentRepository.add(Student(name = firstName, lastName = lastName,id = 0))
        }
    }

    fun addStudentToGroup(id_student:Int, id_group:Int)
    {
        viewModelScope.launch {
            studentRepository.addStudentToGroup(id_student,id_group)
        }
    }

    fun deleteStudentFromGroup(id_student:Int, id_group:Int)
    {
        viewModelScope.launch {
            studentRepository.deleteStudentFromGroup(id_student,id_group)
        }

    }

    fun deleteStudent(student: Student)
    {
        viewModelScope.launch {
            studentRepository.delete(student)
        }
    }


}