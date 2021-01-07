package com.example.notki_nauczyciela.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notki_nauczyciela.model.Student
import com.example.notki_nauczyciela.model.StudentDao
import com.example.notki_nauczyciela.model.StudentGroup
import com.example.notki_nauczyciela.model.StudentGroupDao

class StudentRepository(private val studentDao: StudentDao, private val studentGroupDao: StudentGroupDao) {
    private val readAll:LiveData<List<Student>> = studentDao.allStudent()
//
//    fun idStudentsInGroup(n:Int): LiveData<List<Int>>
//    {
//        return studentGroupDao.studentsInGroup(n)
//    }

    fun studentsInGroup(n: Int):LiveData<List<Student>>
    {
        return studentDao.studentsInGroup(n)
    }


    fun studentsOutGroup(n:Int):LiveData<List<Student>>
    {
        return studentDao.studentsOutGroup(n)
    }

    suspend fun add(student:Student) {
        studentDao.insertStudent(student)
    }

    suspend fun addStudentToGroup(id_student:Int,id_group:Int)
    {
        studentGroupDao.insert(StudentGroup(id=0,student_id=id_student,group_id = id_group))
    }

    suspend fun deleteStudentFromGroup(n: Int,m: Int)
    {
        studentGroupDao.deleteStudentFromGroup(n,m)
    }

    suspend fun delete(student:Student)=studentDao.delete(student)

}