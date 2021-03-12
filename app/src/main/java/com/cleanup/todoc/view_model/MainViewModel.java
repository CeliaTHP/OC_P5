package com.cleanup.todoc.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

    //Get our methods to interact with task database
    private final TaskRepository taskRepository;

    //Get our methods to interact with project database
    private final ProjectRepository projectRepository;

    //Set an executor to call our methods in asynchronous (not on the main thread)
    private final Executor executor;

    public MainViewModel(TaskRepository taskRepository, ProjectRepository projectRepository, Executor executor) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    /**
     * TASK
     */

    public void deleteTask(Task task) {
        executor.execute(() -> taskRepository.deleteTask(task));
    }

    public void createTask(Task task) {
        executor.execute(() -> taskRepository.createTask(task));
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    /**
     * PROJECT
     */

    public LiveData<Project> getProject(long id) {
        return projectRepository.getProject(id);
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectRepository.getAllProjects();
    }


}

