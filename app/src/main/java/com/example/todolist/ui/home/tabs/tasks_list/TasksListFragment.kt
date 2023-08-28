package com.example.todolist.ui.home.tabs.tasks_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.TaskDataBase
import com.example.todolist.databinding.FragmentTasksListBinding

class TasksListFragment : Fragment() {
    lateinit var binding: FragmentTasksListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        loadTasks()
    }

    fun loadTasks() {
        context?.let {
            val tasks = TaskDataBase.getInstance(it)
                .tasksDao()
                .getAllTasks()
            adapter.bindTasks(tasks)
        }
    }

    val adapter = TasksAdapter(null)
    private fun initViews() {
        binding.recyclerview.adapter = adapter
    }
}