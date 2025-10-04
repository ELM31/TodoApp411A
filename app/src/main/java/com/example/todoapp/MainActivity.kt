package com.example.todoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

// ============ Data Class ============
data class Task(val id: Int, val label: String, val completed: Boolean = false)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                TodoAppScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAppScreen() {
    val context = LocalContext.current  // Used for Toasts

    // State: list of tasks
    var tasks by rememberSaveable { mutableStateOf(listOf<Task>()) }
    var nextId by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("To-Do App") }) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                // Input field + Add button
                TaskInputField { taskLabel ->
                    val trimmed = taskLabel.trim()
                    if (trimmed.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Task cannot be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        tasks = tasks + Task(id = nextId++, label = trimmed)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Active tasks
                val activeTasks = tasks.filter { !it.completed }
                if (activeTasks.isNotEmpty()) {
                    Text("Active Items", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    TaskList(
                        tasks = activeTasks,
                        onToggleCompleted = { id ->
                            tasks = tasks.map { if (it.id == id) it.copy(completed = true) else it }
                        },
                        onDelete = { id ->
                            tasks = tasks.filter { it.id != id }
                        }
                    )
                } else {
                    Text("No active items", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Completed tasks
                val completedTasks = tasks.filter { it.completed }
                if (completedTasks.isNotEmpty()) {
                    Text("Completed Items", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    TaskList(
                        tasks = completedTasks,
                        onToggleCompleted = { id ->
                            tasks = tasks.map { if (it.id == id) it.copy(completed = false) else it }
                        },
                        onDelete = { id ->
                            tasks = tasks.filter { it.id != id }
                        }
                    )
                } else {
                    Text("No completed items", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    )
}

// ============ Input Field ============
@Composable
fun TaskInputField(onAdd: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Enter task") },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = {
            onAdd(text)
            text = ""
        }) {
            Text("Add")
        }
    }
}

// ============ Task List ============
@Composable
fun TaskList(
    tasks: List<Task>,
    onToggleCompleted: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = task.completed,
                        onCheckedChange = { onToggleCompleted(task.id) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(task.label)
                }
                IconButton(onClick = { onDelete(task.id) }) {
                    Icon(Icons.Filled.Close, contentDescription = "Delete task")
                }
            }
        }
    }
}

// ============ Custom Theme ============
val myColors = darkColorScheme(
    primary = Color(0xFFD01BE0),
    secondary = Color(0xFFFFFFFF),
    background = Color(0xFF343434),
    surface = Color(0xFFD01BE0),
    onPrimary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF)
)

@Composable
fun TodoAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = myColors,
        typography = Typography(),
        content = content
    )
}
