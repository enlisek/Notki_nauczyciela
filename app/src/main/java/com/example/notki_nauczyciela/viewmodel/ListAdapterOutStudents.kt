package com.example.notki_nauczyciela.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.notki_nauczyciela.R
import com.example.notki_nauczyciela.model.Student

class ListAdapterOutStudents(var students: LiveData<List<Student>>,val viewModel: StudentViewModel): RecyclerView.Adapter<ListAdapterOutStudents.Holder>() {
    lateinit  var context: Context
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row_student,parent,false) as View

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textView1Student)
        val textView2=holder.itemView.findViewById<TextView>(R.id.textView2Student)
        textView1.text=students.value?.get(position)?.name
        textView2.text=students.value?.get(position)?.lastName
        val buttonSelectStudent=holder.itemView.findViewById<Button>(R.id.buttonSelectStudent)
        val buttonDeleteStudent=holder.itemView.findViewById<Button>(R.id.buttonDeleteStudent)

        buttonSelectStudent.setOnClickListener {
            viewModel.addStudentToGroup(students.value?.get(position)!!.id,viewModel.currentGroup.value!!.id)
            viewModel.updateStudents()
        }

        buttonDeleteStudent.setOnClickListener {
            viewModel.deleteStudent(students.value?.get(position)!!)
            viewModel.updateStudents()
        }
    }

    override fun getItemCount(): Int {
        return students.value?.size?:0
    }
}