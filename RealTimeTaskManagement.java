// Import necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Define a class to represent a Task
class Task {
    // Task properties
    private String name;
    private String priority;    private String dueDate;
    boolean completed;

    // Constructor to initialize Task object
    public Task(String name, String priority, String dueDate) {
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    // Getter methods for Task properties
    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean getCompleted() {
        return completed;
    }

    // Setter methods for Task properties
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setComplete(boolean completed) {
        this.completed = completed;
    }
}

// Define a class to manage tasks
class TaskManager {
    // List to store tasks
    ArrayList<Task> tasks = new ArrayList<Task>();

    // Method to add a new task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to update the priority of a task
    public void updateTaskPriority(String taskName, String newPriority) {
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                task.setPriority(newPriority);
                System.out.println("\n\tTask priority updated.");
                return;
            }
        }
        System.out.println("\n\tTask not found.");
    }

    // Method to update the due date of a task
    public void updateTaskDueDate(String taskName, String newDueDate) {
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                task.setDueDate(newDueDate);
                System.out.println("\n\tTask due date updated.");
                return;
            }
        }
        System.out.println("\tTask not found.");
    }

    // Method to mark a task as completed
    public void MarkAs_Completed(String taskName) {
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                System.out.println("\n\tThe Task Is Completed Successfully...!");
                System.out.println("-------------------------------------------");
                task.completed = true;
                return;
            }
        }
        System.out.println("\n\tTask not Found..");
    }

    // Method to retrieve the list of tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

// Main class for RealTimeTaskManagement application
public class RealTimeTaskManagement {
    // GUI components
    private static JTextArea outputTextArea;
    private static TaskManager taskManager;

    // Main method to start the application
    public static void main(String[] args) {
       createAndShowGUI();
    }

    // Method to create and show the GUI
    private static void createAndShowGUI() {
        taskManager = new TaskManager();

        // Create the main JFrame
        JFrame frame = new JFrame("Task Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,600);

        // Create the main panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(234, 249, 234)); 

        // Create the output text area with JScrollPane
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 18)); 
        outputTextArea.setBackground(new Color(220, 240, 240)); 
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the input panel with GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.setBackground(new Color(255, 230, 230));
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Define fonts for labels and buttons
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);

        // Create input fields and labels for task details
        JTextField taskNameField = new JTextField();
        JTextField priorityField = new JTextField();
        JTextField dueDateField = new JTextField();

        JLabel taskNameLabel = new JLabel("Task Name:");
        taskNameLabel.setFont(labelFont);
        inputPanel.add(taskNameLabel);
        inputPanel.add(taskNameField);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setFont(labelFont);
        inputPanel.add(priorityLabel);
        inputPanel.add(priorityField);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setFont(labelFont);
        inputPanel.add(dueDateLabel);
        inputPanel.add(dueDateField);

        // Create buttons for different task management actions
        JButton addButton = new JButton("Add Task");
        addButton.setBackground(new Color(230, 230, 255)); 
        addButton.setFont(buttonFont);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve task details from input fields
                String name = taskNameField.getText();
                String priority = priorityField.getText();
                String dueDate = dueDateField.getText();
                // Create a new Task object
                Task task = new Task(name, priority, dueDate);
                // Add the task to the TaskManager
                taskManager.addTask(task);
                // Clear input fields
                taskNameField.setText("");
                priorityField.setText("");
                dueDateField.setText("");
                // Update the task list in the text area
                printTasks();
            }
        });

        JButton updatePriorityButton = new JButton("Update Task Priority");
        updatePriorityButton.setBackground(new Color(230, 230, 255)); 
        updatePriorityButton.setFont(buttonFont);
        updatePriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve task details from input fields
                String taskName = taskNameField.getText();
                String newPriority = priorityField.getText();
                boolean taskFound = false;
                // Loop through tasks to find the specified task
                for (Task task : taskManager.getTasks()) {
                    if (task.getName().equals(taskName)) {
                        // Update the priority of the task
                        task.setPriority(newPriority);
                        // Display message for successful update
                        outputTextArea.append("\nTask priority updated.");
                        taskFound = true;
                        break;
                    }
                }
                // If task is not found, show an error message
                if (!taskFound) {
                    JOptionPane.showMessageDialog(null, "Task not found.", "Task Not Found", JOptionPane.ERROR_MESSAGE);
                }
                // Clear input fields
                taskNameField.setText("");
                priorityField.setText("");
                // Update the task list in the text area
                printTasks();
            }
        });

        JButton updateDueDateButton = new JButton("Update Task Due Date");
        updateDueDateButton.setBackground(new Color(230, 230, 255));
        updateDueDateButton.setFont(buttonFont);
        updateDueDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve task details from input fields
                String taskName = taskNameField.getText();
                String newDueDate = dueDateField.getText();
                boolean taskFound = false;
                // Loop through tasks to find the specified task
                for (Task task : taskManager.getTasks()) {
                    if (task.getName().equals(taskName)) {
                        // Update the due date of the task
                        task.setDueDate(newDueDate);
                        // Display message for successful update
                        outputTextArea.append("\nTask due date updated.");
                        taskFound = true;
                        break;
                    }
                }
                // If task is not found, show an error message
                if (!taskFound) {
                    JOptionPane.showMessageDialog(null, "Task not found.", "Task Not Found", JOptionPane.ERROR_MESSAGE);
                }
                // Clear input fields
                taskNameField.setText("");
                dueDateField.setText("");
                // Update the task list in the text area
                printTasks();
            }
        });

        JButton markAsCompletedButton = new JButton("Mark As Task Completed");
        markAsCompletedButton.setBackground(new Color(230, 230, 255)); 
        markAsCompletedButton.setFont(buttonFont);
        markAsCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve task details from input fields
                String taskName = taskNameField.getText();
                boolean taskFound = false;
                // Loop through tasks to find the specified task
                for (Task task : taskManager.getTasks()) {
                    if (task.getName().equals(taskName)) {
                        // Mark the task as completed using TaskManager
                        taskManager.MarkAs_Completed(taskName);
                        // Display message for successful completion
                        outputTextArea.append("\nTask completed.");
                        taskFound = true;
                        break;
                    }
                }
                // If task is not found, show an error message
                if (!taskFound) {
                    JOptionPane.showMessageDialog(null, "Task not found.", "Task Not Found", JOptionPane.ERROR_MESSAGE);
                }
                // Clear input fields
                taskNameField.setText("");
                // Update the task list in the text area
                printTasks();
            }
        });

        // Create an "Exit" button to close the application
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(230, 230, 255)); 
        exitButton.setFont(buttonFont);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Add all buttons to the input panel
        inputPanel.add(addButton);
        inputPanel.add(updatePriorityButton);
        inputPanel.add(updateDueDateButton);
        inputPanel.add(markAsCompletedButton);
        inputPanel.add(exitButton);

        // Add the main panel to the frame and display the GUI
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    // Method to print the list of tasks in the text area
    private static void printTasks() {
        outputTextArea.setText("");
        outputTextArea.append("===== Task List =====\n");
        for (Task task : taskManager.getTasks()) {
            outputTextArea.append("Name        :- " + task.getName() + "\n");
            outputTextArea.append("Priority      :- " + task.getPriority() + "\n");
            outputTextArea.append("Due Date  :- " + task.getDueDate() + "\n");
            if (task.getCompleted()) {
                outputTextArea.append("Task is successfully completed..!\n");
                outputTextArea.append("-------------------------------------------\n");
            } else {
                outputTextArea.append("Task is Not successfully completed..!\n");
                outputTextArea.append("--------------------------------------------\n");
            }
            outputTextArea.append("\n");
        }
}
}
