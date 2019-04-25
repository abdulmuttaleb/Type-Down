package com.ahmad.typedown.Model

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

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
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        private val roomCallback =  object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    //Class for AsyncTask to populate Database
    private class PopulateDbAsyncTask(database: NoteDatabase?): AsyncTask<Unit, Unit,Unit>(){

        val noteDao = database?.noteDao()

        override fun doInBackground(vararg params: Unit?) {
            noteDao?.insert(Note("title 1","description 1", 1))
            noteDao?.insert(Note("title 2","description 2", 2))
            noteDao?.insert(Note("title 3","description 3", 3))

        }
    }

}