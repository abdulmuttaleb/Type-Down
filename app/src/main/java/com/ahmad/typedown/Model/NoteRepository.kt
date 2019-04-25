package com.ahmad.typedown.Model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Update
import com.ahmad.typedown.NoteDatabase

class NoteRepository (application:Application){

    //Static NoteRepository
    companion object{
        //Static class for inserting note asynchronously
        private class InsertNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note,Unit,Unit>(){
            val noteDao = noteDao
            override fun doInBackground(vararg params: Note?) {
                noteDao.insert(params[0]!!)
            }
        }

        //Static class for updating note asynchronously
        private class UpdateNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note,Unit,Unit>(){
            val noteDao = noteDao
            override fun doInBackground(vararg params: Note?) {
                noteDao.update(params[0]!!)
            }
        }

        //Static class for deleting note asynchronously
        private class DeleteNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note,Unit,Unit>(){
            val noteDao = noteDao
            override fun doInBackground(vararg params: Note?) {
                noteDao.delete(params[0]!!)
            }
        }
        //static class to delete all notes
        private class DeleteAllNotesAsyncTask(noteDao: NoteDao) : AsyncTask<Unit,Unit,Unit>(){
            val noteDao = noteDao
            override fun doInBackground(vararg params: Unit?) {
                noteDao.deleteAllNotes()
            }
        }
    }

    private var noteDao: NoteDao

    private var allNotes: LiveData<List<Note>>

    init {
        val noteDatabase:NoteDatabase = NoteDatabase.getInstance(application.applicationContext)!!
        noteDao = noteDatabase.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note){
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note){
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note){
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes(){
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    fun getAllNotes():LiveData<List<Note>>{
      return allNotes
    }

}