package com.example.todolist.ui.home.tabs.tasks_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.TaskDataBase
import com.example.todoapp.model.Task
import com.example.todolist.databinding.FragmentTasksListBinding
import com.example.todolist.ui.Constants
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

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
                .getTasksByDate(selectedDate.timeInMillis)
            adapter.bindTasks(tasks.toMutableList())
        }
    }

    val adapter = TasksAdapter(null)
    val selectedDate = Calendar.getInstance()

    init {
        selectedDate.set(Calendar.HOUR, 0)
        selectedDate.set(Calendar.MINUTE, 0)
        selectedDate.set(Calendar.SECOND, 0)
        selectedDate.set(Calendar.MILLISECOND, 0)
    }

    private fun initViews() {
        binding.recyclerview.adapter = adapter
        adapter.onItemDeleteListener = TasksAdapter.OnItemClickListener { position, task ->
            deleteTaskFromDatabase(task)
            adapter.taskDeleted(task)
        }
        adapter.onItemUpdatedListener = TasksAdapter.OnItemClickListener { position, task ->
            val intent = Intent(activity, UpdateTaskActivity::class.java)
            intent.putExtra(Constants.TASK_OBJECT, task)
            startActivity(intent)
        }
        adapter.onButtonClickedListener = TasksAdapter.OnItemClickListener { position, task ->
            doneTaskFromDatabase(task)
            adapter.taskUpdated(task)
        }

        binding.calendarView.setSelectedDate(CalendarDay.today())
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                //reload tasks in selected date
                selectedDate.set(Calendar.YEAR, date.year)
                selectedDate.set(Calendar.MONTH, date.month - 1)
                selectedDate.set(Calendar.DAY_OF_MONTH, date.day)
                loadTasks()
            }
        }
    }


    private fun doneTaskFromDatabase(task: Task) {
        task.isDone = true
        TaskDataBase.getInstance(requireContext())
            .tasksDao()
            .updateTask(task)
    }


    private fun deleteTaskFromDatabase(task: Task) {
        TaskDataBase.getInstance(requireContext())
            .tasksDao()
            .deleteTask(task)
    }
}