package com.ahmad.typedown.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.R

class NoteAdapter(var notes:List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){


    fun setAdapterNotes(notes:List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int):Note{
        return notes[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.priority.text = note.priority.toString()
        holder.description.text = note.description
    }

    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.tv_title)
        var priority : TextView = itemView.findViewById(R.id.tv_priority)
        var description : TextView = itemView.findViewById(R.id.tv_description)

    }
}