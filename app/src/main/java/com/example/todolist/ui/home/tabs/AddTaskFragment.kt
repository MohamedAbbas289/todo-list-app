package com.example.todolist.ui.home.tabs

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.TaskDataBase
import com.example.todoapp.model.Task
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddTask.setOnClickListener {
            createTask()
        }
        binding.dateContainer.setOnClickListener {
            showDatePickerDialog()
        }
    }

    val calendar = Calendar.getInstance()
    private fun showDatePickerDialog() {
        context?.let {
            val dialog = DatePickerDialog(it)
            dialog.setOnDateSetListener { datePicker, day, month, year ->
                binding.date.text = "$year-${month + 1}-$day"
                calendar.set(day, month, year)

                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
            }
            dialog.show()
        }
    }

    private fun valid(): Boolean {
        var isValid = true
        if (binding.title.text.toString().isNullOrBlank()) {
            binding.titleContainer.error = "please enter title"
            isValid = false
        } else {
            binding.titleContainer.error = null
        }
        if (binding.description.text.toString().isNullOrBlank()) {
            binding.descriptionContainer.error = "please enter description"
            isValid = false
        } else {
            binding.descriptionContainer.error = null
        }
        if (binding.date.text.toString().isNullOrBlank()) {
            binding.dateContainer.error = "please enter date"
            isValid = false
        } else {
            binding.dateContainer.error = null
        }
        return isValid
    }

    private fun createTask() {
        if (!valid()) {
            return
        }
        val task = Task(
            name = binding.title.text.toString(),
            description = binding.description.text.toString(),
            dateTime = calendar.timeInMillis
        )
        TaskDataBase.getInstance(requireContext())
            .tasksDao()
            .insertTask(task)
        onTaskAddedListener?.onTaskAdded()
        dismiss()
    }

    var onTaskAddedListener: OnTaskAddedListener? = null

    fun interface OnTaskAddedListener {
        fun onTaskAdded()
    }
}