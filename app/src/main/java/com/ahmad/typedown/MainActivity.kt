package com.ahmad.typedown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.ViewModel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel:NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer {
            //update RecyclerView
        })
    }
}