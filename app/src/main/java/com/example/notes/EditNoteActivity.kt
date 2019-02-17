package com.example.notes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.viewmodel.EditViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.android.synthetic.main.content_edit_note.view.*
import kotlinx.android.synthetic.main.note_list_item.view.*

class EditNoteActivity : AppCompatActivity() {

    private var mEditViewModel : EditViewModel? = null
    private var mEditActivityBinding: ActivityEditNoteBinding? = null
    private var mNewNote: Boolean = false
    private var mEditText: EditText? = null
    private var mEditing :Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mEditActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mEditText = mEditActivityBinding!!.contentMain.editText

        if (savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean("Editing_key")
        }
        initViewModel()
        displayTextViewFromIntentOrNewText()
    }

    private fun displayTextViewFromIntentOrNewText() {
        val key = intent.getIntExtra("Note_key" , -1)

        if (key != -1) {
            title = getString(R.string.edit_note)
            mEditViewModel!!.getNoteById(key)
        }
        else {
            title = getString(R.string.new_note)
            mNewNote = true
        }
    }

    private fun initViewModel() {

        mEditViewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        mEditViewModel!!.mLiveNote.observe(this, Observer { data ->
            if (data != null && mEditing != true )
                mEditText!!.setText(data.text)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()

        saveNoteAndReturn()

    }

    private fun saveNoteAndReturn() {
        if (mEditText!!.text != null)
            mEditViewModel!!.saveNote(mEditText!!.text.toString())
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if (!mNewNote) {

            menuInflater.inflate(R.menu.menu_edit_note, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {

            android.R.id.home -> {
                saveNoteAndReturn()
                true
            }
            R.id.action_delete -> {
                deleteNote()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteNote() {
        mEditViewModel!!.deleteNote()
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("Editing_key", true)
        super.onSaveInstanceState(outState)
    }
}
