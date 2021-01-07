package com.example.notki_nauczyciela.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.Mark
import com.example.notki_nauczyciela.model.MyDatabase
import com.example.notki_nauczyciela.model.Student
import com.example.notki_nauczyciela.model.repositories.MarkRepository
import com.example.notki_nauczyciela.model.repositories.StudentRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RaportViewModel(application: Application): AndroidViewModel(application) {

    val sdf = SimpleDateFormat("dd/M/yyyy")
    private var today = sdf.format(Date())

    lateinit var listOfMarks: LiveData<List<Mark>>
    private val markRepository: MarkRepository

    init {
        markRepository = MarkRepository(MyDatabase.getDatabase(application).markDao())
        updateMarks()
    }

    fun updateMarks()
    {
        listOfMarks=markRepository.marksToday(today)
    }




}