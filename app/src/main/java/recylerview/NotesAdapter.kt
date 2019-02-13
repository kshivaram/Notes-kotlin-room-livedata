package recylerview

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import database.NoteEntity
import android.support.v7.util.DiffUtil
import com.example.notes.databinding.NoteListItemBinding


class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.NotesViewHolder>(NotesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                com.example.notes.R.layout.note_list_item, parent, false)
            )
    }


    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        getItem(position).let {
            noteEntity ->
            with(holder) {
                itemView.tag = noteEntity
                bind(noteEntity)
            }
        }
    }


   open class NotesViewHolder(private val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {

       fun bind(data: NoteEntity) {
           with(binding) {
               noteEntity = data
           }
       }

    }

    class NotesDiffUtil : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }
}