package com.example.todoapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.databinding.ActivityHomeBinding
import com.example.todolist.ui.home.tabs.AddTaskFragment
import com.example.todolist.ui.home.tabs.SettingsFragment
import com.example.todolist.ui.home.tabs.tasks_list.TasksListFragment
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    var tasksListFragmentRef: TasksListFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_tasks_list -> {
                    tasksListFragmentRef = TasksListFragment()
                    showFragment(tasksListFragmentRef!!)
                }

                R.id.navigation_tasks_setting -> {
                    showFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigationView.selectedItemId = R.id.navigation_tasks_list
        binding.btnFloatingAdd.setOnClickListener {
            showAddTaskBottomSheet()
        }
    }

    private fun showAddTaskBottomSheet() {
        val addTaskSheet = AddTaskFragment()
        addTaskSheet.onTaskAddedListener = AddTaskFragment.OnTaskAddedListener {
            Snackbar.make(binding.root, "Task Added", Snackbar.LENGTH_SHORT).show()
            tasksListFragmentRef?.loadTasks()
        }
        addTaskSheet.show(supportFragmentManager, "")
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}


