package com.example.todolist.ui.home.tabs.tasks_list

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.TaskDataBase
import com.example.todoapp.model.Task
import com.example.todolist.databinding.ActivityUpdateTaskBinding
import com.example.todolist.ui.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class UpdateTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initParams()
        initView()
        binding.dateTv.setOnClickListener { showDatePickerDialog() }
        binding.backBtn.setOnClickListener { finish() }
        binding.saveBtn.setOnClickListener {
            updateTaskFromDatabase()
        }

    }

    private fun initView() {
        binding.title.text = task?.name?.let { Editable.Factory.getInstance().newEditable(it) }
        binding.description.text =
            task?.description?.let { Editable.Factory.getInstance().newEditable(it) }
//        binding.dateTv.text = task?.dateTime?.let { formatTime(it) } ?: "No date available"
    }


    private fun updateTaskFromDatabase() {
        if (!valid()) {
            return
        }
        task?.name = binding.title.text.toString()
        task?.description = binding.description.text.toString()
        task?.dateTime = calendar.timeInMillis
        TaskDataBase.getInstance(this)
            .tasksDao()
            .updateTask(task!!)
        finish()
    }

    var task: Task? = null
    private fun initParams() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            task = intent.getSerializableExtra(Constants.TASK_OBJECT, Task::class.java)
        } else {
            task = intent.getSerializableExtra(Constants.TASK_OBJECT) as Task
        }
    }

    fun formatTime(milliseconds: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date(milliseconds)
        return sdf.format(date)
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
        if (binding.dateTv.text.toString().isNullOrBlank()) {
            binding.dateContainer.error = "please enter date"
            isValid = false
        } else {
            binding.dateContainer.error = null
        }
        return isValid
    }

    val calendar = Calendar.getInstance()
    private fun showDatePickerDialog() {
        val dialog = DatePickerDialog(this)
        dialog.setOnDateSetListener { datePicker, year, month, day ->
            binding.dateTv.text = "$year-${month + 1}-$day"
            calendar.set(year, month, day)
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
        }
        dialog.show()
    }

}