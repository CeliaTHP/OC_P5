package com.cleanup.todoc.view_model;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    TaskRepository taskRepository;
    ProjectRepository projectRepository;

    public MainViewModel(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * TASK
     */

    public void deleteTask(Task task) {
        taskRepository.deleteTask(task);
    }

    public long createTask(Task task) {
        return taskRepository.createTask(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    /**
     * PROJECT
     */

    public Project getProject(long id) {
        return projectRepository.getProject(id);
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectRepository.getAllProjects();
    }



}

