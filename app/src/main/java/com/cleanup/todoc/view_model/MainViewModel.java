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

    public void deleteTask(Task task) {
        taskRepository.deleteTask(task);
    }

    public long createTask(Task task) {
        return taskRepository.createTask(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Project getProject(long id) {
        return projectRepository.getProject(id);
    }

/*
    /**
     * Returns the project with the given unique identifier, or null if no project with that
     * identifier can be found.
     *
     * @param id the unique identifier of the project to return
     * @return the project with the given unique identifier, or null if it has not been found

    @Nullable
    public Project getProjectById(long id) {
        for (Project project : Project.getAllProjects()) {
            if (project.getId() == id)
                return project;
        }
        return null;
    }
*/


}

