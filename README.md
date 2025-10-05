Todo App for 411A

This app features:

Ability to add new tasks with the input filed, and press with the add button 
Shows if the task list is empty 
Contain two actions 
Active tasks 
Completed Tasks
Click of the checkbox will move active tasks to complete tasks 
Unique Color scheme and typography using Material3 design 

Some concepts that are demonstrated 

Data Class - Task models the todo items with an ID, label and completion status 
State Management - mutableStateOf and RememberSaveable
Remember / rememberSaveable - UI state to be retained
State Hoisting - composables TaskList and TaskInputFields are stateless, and receive data from TodoAppScreen
Composable Functions - TodoAppScreen(), TaskInputField(), TaskList(), TodoAppTheme(), are all composable functions that help with the UI

Data Class Task
<img width="585" height="53" alt="Capture1" src="https://github.com/user-attachments/assets/85261f85-6c34-406e-8e0c-bc0aed94d4a8" />

Represents each to-do item

MainActivity
<img width="354" height="201" alt="Capture" src="https://github.com/user-attachments/assets/ca02281f-566b-4bf2-814c-1b3c6bceb3f3" />

The app’s entry point 


TodoAppScreen()
The main composable function that controls what appears on the screen.
It holds all the smaller UI pieces together and manages states, like the list of to-do items and new input text.

Some important parts inside the TodoAppScreen() include:

State management with tasks and nextID  
<img width="423" height="68" alt="Capture3" src="https://github.com/user-attachments/assets/61b23f51-5f0c-43b6-a622-500600f0002f" />

This is integral for state management. 
Tasks holds all the current tasks in the app while new tasks holds what the user types in the text box 
Remember is used to keep data alive during recomposition

Layout using Scaffold and Column 
<img width="371" height="173" alt="Capture4" src="https://github.com/user-attachments/assets/261991c5-99bf-4011-8f3b-987a0be5ffd3" />

Scaffold is a Material layout helper that allows for slots in UI regions for the topbar
Input section with TaskInputField 
Column allows us to stack all of our UI elements vertically. We use Modifier.padding(innerPadding) to prevent overlapping with the TopAppBar 

Task Input Section 
<img width="372" height="221" alt="Capture5" src="https://github.com/user-attachments/assets/128a9777-5c23-48d5-a732-b44f6ea2e751" />

Handles user input by using composable TaskInputField(), adds a new task object into the task list 

Active Tasks Section
<img width="406" height="250" alt="Capture6" src="https://github.com/user-attachments/assets/9e42fb96-24f6-464a-95ed-324d93d15fc7" />

Filters and shows only the tasks that are not completed, toggles them to done, or deletes them 

Completed Tasks Section
<img width="441" height="262" alt="Capture7" src="https://github.com/user-attachments/assets/1ae8abd2-7100-4f43-8693-25aa8feda5fb" />

Reverses the logic from above, displays completed tasks, and lets you deletes them
TaskInputField()
<img width="324" height="352" alt="Capture8" src="https://github.com/user-attachments/assets/03874bbf-178c-441c-b097-07837d2853c1" />

Handles adding new tasks


TaskList()
<img width="376" height="410" alt="Capture9" src="https://github.com/user-attachments/assets/00c3bab8-8886-4c7b-8b8b-7b3a25cacdbe" />

Displays a list of taskings using LazyColumn


TodoAppTheme()  + val myColors
<img width="308" height="336" alt="Capture10" src="https://github.com/user-attachments/assets/67699dd5-72dd-4ef2-aabe-c263f54d3c8b" />

Defines the color scheme and typography 

Some examples of the app 
<img width="327" height="318" alt="Capture11" src="https://github.com/user-attachments/assets/00387e6a-4fa4-4c9c-b285-59f244e395e8" />
<img width="338" height="296" alt="Capture12" src="https://github.com/user-attachments/assets/c8fbb355-d7b2-4c89-b27b-fb639126a154" />



