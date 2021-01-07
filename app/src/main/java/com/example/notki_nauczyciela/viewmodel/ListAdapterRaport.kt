package com.example.notki_nauczyciela.viewmodel

import android.annotation.SuppressLint
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

class ListAdapterRaport(var data: LiveData<List<Mark>>): RecyclerView.Adapter<ListAdapterRaport.Holder>() {
    lateinit  var context: Context
    class Holder(view: View): RecyclerView.ViewHolder(view)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row_raport,parent,false) as View

        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textViewRaport)
        textView1.text="ID STUDENTA: ${data.value?.get(position)!!.student_id} | ID GRUPY: ${data.value?.get(position)!!.group_id} | OCENA: ${data.value?.get(position)!!.value} | KOMENTARZ: ${data.value?.get(position)!!.note} | DATA: ${data.value?.get(position)!!.date}"
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}