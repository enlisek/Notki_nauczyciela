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
import com.example.notki_nauczyciela.model.Note

class ListAdapterNote(var data: LiveData<List<Note>>, val noteViewModel: NoteViewModel): RecyclerView.Adapter<ListAdapterNote.NoteHolder>() {

    inner class NoteHolder(view: View): RecyclerView.ViewHolder(view){
        val textView1=view.findViewById<TextView>(R.id.textViewNote)
        val buttonDeleteNote= view.findViewById<Button>(R.id.buttonDeleteNote)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row_note,parent,false) as View
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        holder.textView1.text=data.value?.get(position)!!.note
        holder.buttonDeleteNote.setOnClickListener {
            noteViewModel.deleteNote(data.value?.get(position)!!)
            noteViewModel.updateNotes()
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}
