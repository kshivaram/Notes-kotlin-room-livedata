package recylerview

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.databinding.NoteListItemBinding
import database.NoteEntity

class NotesEntityAdapter : ListAdapter<NoteEntity, NotesEntityAdapter.MyViewHolder>(MyDiffUtil()) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

       return MyViewHolder(DataBindingUtil.inflate(
           LayoutInflater.from(p0.context),
            R.layout.note_list_item, p0, false)
       )
    }

    override fun onBindViewHolder(viewHolder:  MyViewHolder, position: Int) {
        with(viewHolder) {
            bind(getItem(position))
        }
    }

    class MyViewHolder(private var binding:NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: NoteEntity) {
            binding.noteEntity = notes
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