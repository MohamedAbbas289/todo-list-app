package com.example.todolist.ui.home.tabs.tasks_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.model.Task
import com.example.todolist.R
import com.example.todolist.R.color
import com.example.todolist.databinding.ItemTaskBinding


class TasksAdapter(var tasks: MutableList<Task>?) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = tasks?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var task: Task = tasks!![position]
        with(holder) {
            binding.title.text = task.name
            binding.description.text = task.description
            if (task.isDone) {
                binding.title.setTextColor(
                    ContextCompat.getColor(itemView.context, color.is_done_color)
                )
                binding.line.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, color.is_done_color)
                )
                binding.doneBtn.isVisible = false
                binding.isDoneTxt.isVisible = true
            } else {
                binding.title.setTextColor(
                    ContextCompat.getColor(itemView.context, color.colorPrimary)
                )
                binding.doneBtn.setBackgroundDrawable(
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.round_corner)
                )
                binding.line.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, color.colorPrimary)
                )
                binding.doneBtn.isVisible = true
                binding.isDoneTxt.isVisible = false
            }

            if (onItemDeleteListener != null) {
                binding.swipeLayout.close(true)
                binding.deleteView.setOnClickListener {
                    onItemDeleteListener?.onItemClick(position, task)
                }
            }
            if (onItemUpdatedListener != null) {
                binding.dragItem.setOnClickListener {
                    onItemUpdatedListener?.onItemClick(position, task)
                }
            }
            if (onButtonClickedListener != null) {
                binding.doneBtn.setOnClickListener {
                    onButtonClickedListener?.onItemClick(position, task)
                }
            }


        }
    }


    fun bindTasks(tasks: MutableList<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    fun taskDeleted(task: Task) {
        val position = tasks?.indexOf(task)
        tasks?.remove(task)
        notifyItemRemoved(position!!)
    }

    fun taskUpdated(task: Task) {
        val position = tasks?.indexOf(task)
        tasks?.set(position!!, task)
        notifyItemChanged(position!!)
    }


    var onItemDeleteListener: OnItemClickListener? = null
    var onItemUpdatedListener: OnItemClickListener? = null
    var onButtonClickedListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(position: Int, task: Task)
    }


}