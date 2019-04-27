package com.ahmad.typedown.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.R

class NoteAdapter: ListAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK){

    private var clickListener: OnItemClickListener? = null

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    fun getNoteAt(position: Int):Note{
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.title.text = note.title
        holder.priority.text = note.priority.toString()
        holder.description.text = note.description
    }

    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.tv_title)
        var priority : TextView = itemView.findViewById(R.id.tv_priority)
        var description : TextView = itemView.findViewById(R.id.tv_description)

        init {
            itemView.setOnClickListener{
                val position:Int = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    clickListener?.onItemClick(getItem(position))
                }
            }
        }
    }


    interface OnItemClickListener{
        fun onItemClick(note: Note)
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.clickListener = listener
    }

}