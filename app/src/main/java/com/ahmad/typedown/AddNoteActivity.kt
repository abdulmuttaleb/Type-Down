package com.ahmad.typedown

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class AddNoteActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TITLE:String="com.ahmad.typedown.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION:String="com.ahmad.typedown.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY:String="com.ahmad.typedown.EXTRA_PRIORITY"
        const val EXTRA_ID:String="com.ahmad.typedown.EXTRA_ID"
    }

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priorityPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        initUi()
    }

    private fun initUi() {
        titleEditText = findViewById(R.id.et_title)
        descriptionEditText = findViewById(R.id.et_description)
        priorityPicker = findViewById(R.id.np_priority)
        priorityPicker.minValue = 1
        priorityPicker.maxValue = 10
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if(titleEditText.text.isNullOrEmpty() || descriptionEditText.text.isNullOrEmpty()){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show()
            return
        }

        val data=Intent().apply {
            putExtra(EXTRA_TITLE,titleEditText.text.toString())
            putExtra(EXTRA_DESCRIPTION,descriptionEditText.text.toString())
            putExtra(EXTRA_PRIORITY,priorityPicker.value)
            putExtra(EXTRA_ID,titleEditText.text)
        }

        setResult(RESULT_OK,data)
        finish()

    }

}
