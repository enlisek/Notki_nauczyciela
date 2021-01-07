package com.example.notki_nauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.notki_nauczyciela.model.Mark
import com.example.notki_nauczyciela.model.Note
import com.example.notki_nauczyciela.model.NoteDao

class NoteRepository (private val noteDao: NoteDao){
    suspend fun add(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun delete(note: Note){
        noteDao.deleteNote(note)
    }

    fun allNotes(): LiveData<List<Note>>
    {
        return noteDao.allNotes()
    }


}