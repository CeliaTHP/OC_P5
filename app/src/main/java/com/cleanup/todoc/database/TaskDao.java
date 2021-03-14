package com.cleanup.todoc.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>>getAllTasks();

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    Task getTask(long taskId);

    @Query("SELECT * FROM task_table WHERE project_id = :projectId")
    LiveData<List<Task>>getTasksForProject(long projectId);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("DELETE FROM task_table WHERE id = :taskId")
    void deleteTask(long taskId);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

}
