package com.ahmad.typedown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmad.typedown.Adapter.NoteAdapter
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.ViewModel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel:NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = NoteAdapter(arrayListOf())
        recyclerView.adapter = adapter


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer {
            adapter.setAdapterNotes(it)
        })
    }
}