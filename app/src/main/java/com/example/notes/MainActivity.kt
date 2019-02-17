package com.example.notes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.notes.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*
import com.example.notes.database.NoteEntity
import com.example.notes.recylerview.NotesEntityAdapter
import com.example.notes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityBinding: ActivityMainBinding
    private var notesAdapter : NotesEntityAdapter? = null
    private var mViewModel: MainViewModel? = null
    private var recyclerView: RecyclerView? = null

   private val notesObserver = Observer<List<NoteEntity>> {
        notesAdapter?.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val toolbar = mainActivityBinding.toolbar
        setSupportActionBar(toolbar)

        initRecyclerView()
        initViewModel()

        fab.setOnClickListener {
           val intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel!!.getAllNotes().observe(this,notesObserver)
    }
    private fun initViewModel() {

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel!!.getAllNotes().observe(this, notesObserver)

    }

    override fun onPause() {
        super.onPause()
        mViewModel!!.getAllNotes().removeObserver(notesObserver)
    }

    private fun initRecyclerView() {
        recyclerView  = mainActivityBinding.contentMain.recyclerView

        notesAdapter = NotesEntityAdapter()
        val llm = LinearLayoutManager(this)
        with(recyclerView) {
            this!!.setHasFixedSize(true)
            layoutManager = llm
            adapter = notesAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {


            R.id.action_add -> {
                addSampleData()
                true
            }
            R.id.action_delete_All -> {
                deleteAllData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllData() {
        mViewModel!!.deleteAllData()
    }

    private fun addSampleData() {
        mViewModel!!.addSampleData()
    }
}
