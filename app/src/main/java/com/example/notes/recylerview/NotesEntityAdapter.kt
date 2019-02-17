package com.example.notes.recylerview

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.EditNoteActivity
import com.example.notes.R
import com.example.notes.databinding.NoteListItemBinding
import com.example.notes.database.NoteEntity

class NotesEntityAdapter : ListAdapter<NoteEntity, NotesEntityAdapter.MyViewHolder>(MyDiffUtil()) {
    private var context: Context? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        context = p0.context
       return MyViewHolder(
           DataBindingUtil.inflate(
               LayoutInflater.from(p0.context),
               R.layout.note_list_item, p0, false
           )
       )
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        with(viewHolder) {
            bind(createClicklistener(getItem(position)),getItem(position))
        }
    }

    private fun createClicklistener(notes: NoteEntity): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, EditNoteActivity::class.java)
            intent.putExtra("Note_key", notes.key)
            context!!.startActivity(intent)
        }
    }

    class MyViewHolder(private var binding:NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, notes: NoteEntity) {
            binding.noteEntity = notes
            binding.clicklistener = listener
        }
    }

    class MyDiffUtil : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }
}