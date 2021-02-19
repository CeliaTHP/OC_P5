package com.cleanup.todoc.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Task>> tasks;

    public LiveData<List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<>();
            loadTasks();
        }
        return tasks;
    }

    private void loadTasks() {
    }
}
