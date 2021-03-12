package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    // --- GET ALL PROJECTS ---
    public LiveData<List<Project>> getAllProjects() {
        return this.projectDao.getAllProjects();
    }

    // --- GET PROJECT ---
    public LiveData<Project> getProject(long projectId) {
        return this.projectDao.getProject(projectId);
    }

    // --- CREATE PROJECT ---
    public void createProject(Project project) {
        this.projectDao.createProject(project);
    }

}
