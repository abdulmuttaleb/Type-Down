package com.ahmad.typedown

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmad.typedown.Adapter.NoteAdapter
import com.ahmad.typedown.AddNoteActivity.Companion.EXTRA_DESCRIPTION
import com.ahmad.typedown.AddNoteActivity.Companion.EXTRA_PRIORITY
import com.ahmad.typedown.AddNoteActivity.Companion.EXTRA_TITLE
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.ViewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel:NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:NoteAdapter
    private lateinit var addFab:FloatingActionButton

    companion object{
        const val ADD_NOTE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_main)
        addFab = findViewById(R.id.fab_add_note)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = NoteAdapter(arrayListOf())
        recyclerView.adapter = adapter


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer {
            adapter.setAdapterNotes(it)
        })

        addFab.setOnClickListener {
            val intent:Intent = Intent(this,AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext,"Note deleted.",Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            val title: String = data!!.getStringExtra(EXTRA_TITLE)
            val description:String = data.getStringExtra(EXTRA_DESCRIPTION)
            val priority:Int = data.getIntExtra(EXTRA_PRIORITY,1)

            val note = Note(title,description,priority)
            noteViewModel.insert(note)

            Toast.makeText(this,"Note Saved!",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }
}