# 📝 Todo List App

A native Android task-management application built with **Kotlin** and traditional Android Views.

The application allows users to create, view, update, and manage tasks using local persistence with **Room Database**, while providing date-based task organization through a calendar-driven interface.

## 🚀 Features

* ✅ Create new tasks
* ✏️ Update existing tasks
* 🗑️ Delete tasks
* 📅 Organize tasks by date
* 🗓️ Calendar-based task browsing
* 💾 Persistent local storage with Room
* 📱 XML-based Android UI
* 🔄 Swipe interactions for task actions
* ⚙️ Settings screen
* 🎬 Splash screen
* 🔗 ViewBinding

---

## 📝 Task Management

The application provides the main CRUD operations required for task management.

Users can:

* Add a task
* Select its date
* View tasks for a selected day
* Update task information
* Delete tasks
* Manage tasks directly from the task list

Typical flow:

```text id="todo-list-flow"
Select Date
    ↓
View Tasks
    ↓
Add / Update / Delete Task
    ↓
Room Database
    ↓
Updated Task List
```

---

## 📅 Calendar-Based Organization

The project integrates **MaterialCalendarView** to allow users to browse tasks based on dates.

This makes it possible to organize tasks around a selected day instead of displaying all tasks in a single list.

The calendar acts as the main date-selection component for the task-management flow.

---

## 💾 Room Database

Tasks are stored locally using **Room**.

The local data structure includes:

```text id="todo-room-flow"
Task Model
    ↓
DAO
    ↓
TaskDataBase
    ↓
Application UI
```

Room provides persistent storage so tasks remain available after closing and reopening the application.

---

## 🏗️ Application Structure

The project uses a simple layered Android structure.

```text id="todo-list-architecture"
UI
 ↓
Room DAO
 ↓
Room Database
```

The project separates:

* UI components
* Task models
* Database access
* DAO operations

This keeps task persistence separated from the user interface.

---

## 📂 Project Structure

```text id="todo-list-structure"
com.example.todolist/
│
├── TaskDataBase.kt
│
├── dao/
│   └── Task database operations
│
├── model/
│   └── Task data models
│
└── ui/
    ├── splash/
    │
    └── home/
        ├── HomeActivity.kt
        │
        └── tabs/
            ├── AddTaskFragment.kt
            ├── SettingsFragment.kt
            │
            └── tasks_list/
                ├── TasksAdapter.kt
                ├── TasksListFragment.kt
                └── UpdateTaskActivity.kt
```

---

## 🛠️ Tech Stack

### Language

* **Kotlin**

### Android UI

* XML Layouts
* ViewBinding
* Material Components
* ConstraintLayout
* RecyclerView

### Local Storage

* **Room Database**
* Room DAO

### Calendar

* **MaterialCalendarView**

### Task Interactions

* **SwipeLayout**

### Other

* Kotlin Parcelize
* AndroidX
* Gradle

---

## 🔄 Task List

Tasks are displayed using a RecyclerView-based list.

The task list is handled through:

```text id="todo-list-components"
TasksListFragment
        ↓
TasksAdapter
        ↓
Task Items
```

This keeps list rendering and task-item interaction separated from the surrounding screen logic.

---

## ✏️ Update Task

Existing tasks can be edited through a dedicated:

```text id="todo-update"
UpdateTaskActivity
```

Users can modify previously stored task information and save the changes back to the Room database.

---

## ➕ Add Task

New tasks are created through:

```text id="todo-add"
AddTaskFragment
```

The user can enter the required task information and associate the task with a specific date before saving it locally.

---

## 🔄 Swipe Actions

The application uses a swipe-layout implementation to provide quick task interactions directly from the task list.

This improves usability by allowing common actions without requiring additional navigation.

---

## 🚀 Getting Started

### Prerequisites

You will need:

* Android Studio
* Android SDK
* JDK
* Android device or emulator

---

### Clone the Repository

```bash id="todo-list-clone"
git clone -b development https://github.com/MohamedAbbas289/todo-list-app.git
cd todo-list-app
```

The main application implementation is available on the:

```text id="todo-development"
development
```

branch.

---

## 🔨 Build

Linux / macOS:

```bash id="todo-list-build-linux"
./gradlew assembleDebug
```

Windows:

```bash id="todo-list-build-windows"
gradlew.bat assembleDebug
```

Or open the project using Android Studio and run the `app` module.

---

## 🧪 Testing

Run tests using:

```bash id="todo-list-tests"
./gradlew test
```

---

## 🎯 Project Purpose

This project was built to practice core Android development concepts including:

* Kotlin
* XML UI development
* Room Database
* DAO operations
* CRUD operations
* RecyclerView
* ViewBinding
* Calendar-based task management
* Swipe interactions
* Fragment-based UI
* Local persistence

---

## 👨‍💻 Developer

**Mohamed Ibrahim Abbas**

Android & Kotlin Multiplatform Developer

GitHub: `MohamedAbbas289`
