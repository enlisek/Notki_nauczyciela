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

class ListAdapterMark(var data: LiveData<List<Mark>>,val viewModel: MarkViewModel): RecyclerView.Adapter<ListAdapterMark.Holder>() {
    lateinit  var context: Context
    class Holder(view: View): RecyclerView.ViewHolder(view)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row_mark,parent,false) as View

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textView1=holder.itemView.findViewById<TextView>(R.id.textView1Mark)
        val textView2=holder.itemView.findViewById<TextView>(R.id.textView2Mark)
        textView1.text=data.value?.get(position)?.value?.toString()?:""
        textView2.text=data.value?.get(position)?.note?:""
        val buttonDelete=holder.itemView.findViewById<Button>(R.id.buttonDeleteMark)

        buttonDelete.setOnClickListener {
            viewModel.deleteMark(data.value?.get(position)!!)
            viewModel.updateMarks()
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}