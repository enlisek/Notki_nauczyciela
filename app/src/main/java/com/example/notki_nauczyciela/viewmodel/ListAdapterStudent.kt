package com.example.notki_nauczyciela.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notki_nauczyciela.R
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.Mark
import com.example.notki_nauczyciela.model.Student
import java.util.ArrayList

class ListAdapterStudent(var students: LiveData<List<Student>>, val viewModelMark: MarkViewModel, val viewModelStudent: StudentViewModel): RecyclerView.Adapter<ListAdapterStudent.StudentHolder>() {
    inner class StudentHolder(view: View): RecyclerView.ViewHolder(view){
        val textView1=view.findViewById<TextView>(R.id.textView1Student)
        val textView2=view.findViewById<TextView>(R.id.textView2Student)
        val buttonSelectStudent=view.findViewById<Button>(R.id.buttonSelectStudent)
        val buttonDeleteStudent=view.findViewById<Button>(R.id.buttonDeleteStudent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.one_row_student,parent,false) as View
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {

        holder.textView1.text=students.value?.get(position)?.name
        holder.textView2.text=students.value?.get(position)?.lastName

        holder.buttonSelectStudent.setOnClickListener {
            viewModelMark.setCurrentGroupInMarks(viewModelStudent.currentGroup.value!!)
            viewModelMark.setCurrentStudentInMarks(students.value?.get(position)!!)
            holder.itemView.findNavController().navigate(R.id.action_studentFragment_to_markFragment)
        }

        holder.buttonDeleteStudent.setOnClickListener {
            viewModelStudent.deleteStudentFromGroup(viewModelStudent.currentGroup.value!!.id,students.value?.get(position)!!.id)
            viewModelStudent.updateStudents()
        }
    }

    override fun getItemCount(): Int {
        return students.value?.size?:0
    }
}