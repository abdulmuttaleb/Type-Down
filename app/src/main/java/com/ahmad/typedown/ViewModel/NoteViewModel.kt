package com.ahmad.typedown.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.Model.NoteRepository

class NoteViewModel(application : Application) : AndroidViewModel(application) {
    private var noteRepository:NoteRepository =  NoteRepository(application)
    private var allNotes:LiveData<List<Note>> = noteRepository.getAllNotes()

    fun insert(note: Note){
        noteRepository.insert(note)
    }

    fun update(note: Note){
        noteRepository.update(note)
    }

    fun delete(note: Note){
        noteRepository.delete(note)
    }

    fun deleteAllNotes(){
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }
}