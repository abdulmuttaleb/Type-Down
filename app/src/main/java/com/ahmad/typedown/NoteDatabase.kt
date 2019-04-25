package com.ahmad.typedown

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmad.typedown.Model.Note
import com.ahmad.typedown.Model.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase {

    abstract fun noteDao():NoteDao

    companion object{

        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            //Init instance if it is null making a synchronized instance
            if (instance == null){
                synchronized(NoteDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                            //avoid app crash when increasing database version on upgrading schema
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

    }
}