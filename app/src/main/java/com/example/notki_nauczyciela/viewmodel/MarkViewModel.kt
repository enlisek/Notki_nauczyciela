package com.example.notki_nauczyciela.viewmodel


import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.Mark
import com.example.notki_nauczyciela.model.MyDatabase
import com.example.notki_nauczyciela.model.Student
import com.example.notki_nauczyciela.model.repositories.GroupRepository
import com.example.notki_nauczyciela.model.repositories.MarkRepository
import com.example.notki_nauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MarkViewModel(application: Application): AndroidViewModel(application) {

    private val markRepository: MarkRepository
    private val studentRepository: StudentRepository
    val sdf = SimpleDateFormat("dd/M/yyyy")

    lateinit var listOfMarks: LiveData<List<Mark>>


    private var _currentStudent:MutableLiveData<Student> = MutableLiveData()
    val currentStudent:LiveData<Student>
        get()=_currentStudent

    private var _currentGroup:MutableLiveData<Group> = MutableLiveData()
    val currentGroup:LiveData<Group>
        get()=_currentGroup


    init {
        studentRepository=
            StudentRepository(MyDatabase.getDatabase(application).studentDao(),MyDatabase.getDatabase(application).studentGroupDao())
        markRepository = MarkRepository(MyDatabase.getDatabase(application).markDao())
    }

    fun updateMarks()
    {
        listOfMarks=markRepository.marksOfStudent(currentGroup.value!!.id,currentStudent.value!!.id)
    }

    fun setCurrentGroupInMarks(group: Group)
    {
        _currentGroup.value=group
    }

    fun setCurrentStudentInMarks(student: Student)
    {
        _currentStudent.value=student
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun addMark(value:String, note:String)
    {
        var mark = Mark(id = 0,group_id = currentGroup.value!!.id,student_id = currentStudent.value!!.id,value=value,note=note,date = sdf.format(Date()))
        viewModelScope.launch {
            markRepository.add(mark)
        }
    }

    fun deleteMark(mark:Mark)
    {
        viewModelScope.launch {
            markRepository.delete(mark)
        }
    }




}