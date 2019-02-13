package com.example.notes

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.notes.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*
import database.NoteEntity
import recylerview.NotesEntityAdapter
import utilities.SampleData

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityBinding: ActivityMainBinding
    private var notesList = ArrayList<NoteEntity>()
    private var notesAdapter : NotesEntityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val toolbar = mainActivityBinding.toolbar
        setSupportActionBar(toolbar)

        initRecyclerView()

        fab.setOnClickListener {
           val intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initRecyclerView() {
        val recyclerView  = mainActivityBinding.contentMain.recyclerView
        notesList.addAll(SampleData.getNotes())

        notesAdapter = NotesEntityAdapter()
        val llm = LinearLayoutManager(this)
        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = llm
            adapter = notesAdapter
        }

        notesAdapter?.submitList(notesList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
