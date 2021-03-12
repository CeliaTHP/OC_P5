package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // --- GET ALL TASKS---
    public LiveData<List<Task>> getAllTasks() {
        return this.taskDao.getAllTasks();
    }

    // --- GET TASK
    public Task getTask(long taskId) {
        return this.taskDao.getTask(taskId);
    }


    // --- GET TASKS FOR PROJECT
    public LiveData<List<Task>> getTasksForProject(long projectId) {
        return this.taskDao.getTasksForProject(projectId);
    }

    // --- CREATE TASK---
    public void createTask(Task task) {
        taskDao.createTask(task);
    }

    // --- UPDATE TASK---
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    // --- DELETE TASK---
    public void deleteTask(Task task) {
        taskDao.deleteTask(task);
    }

}
