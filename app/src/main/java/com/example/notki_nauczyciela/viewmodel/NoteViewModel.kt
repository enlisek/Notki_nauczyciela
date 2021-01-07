package com.example.notki_nauczyciela.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notki_nauczyciela.model.Group
import com.example.notki_nauczyciela.model.MyDatabase
import com.example.notki_nauczyciela.model.Note
import com.example.notki_nauczyciela.model.repositories.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {



    lateinit var listOfNotes: LiveData<List<Note>>
    private val noteRepository: NoteRepository

    init {
        noteRepository = NoteRepository(MyDatabase.getDatabase(application).noteDao())
        updateNotes()
    }

    fun updateNotes()
    {
        listOfNotes=noteRepository.allNotes()
    }

    fun addNote(note:String)
    {
        viewModelScope.launch {
            noteRepository.add(Note(id=0,note=note))
        }
    }

    fun deleteNote(note:Note)
    {
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }



}