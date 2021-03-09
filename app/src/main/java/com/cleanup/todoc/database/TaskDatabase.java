package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(version = 1, entities = {Project.class, Task.class}, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static volatile TaskDatabase INSTANCE;

    public abstract TaskDao getTaskDao();

    public abstract ProjectDao getProjectDao();

    public static TaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "TaskDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                super.onCreate(db);

                ContentValues contentTask = new ContentValues();
                ContentValues contentProject = new ContentValues();

                contentProject.put("id",1L);
                contentProject.put("name","Projet Tartampion");
                contentProject.put("color", 0xFFEADAD1);
                db.insert("project_table",OnConflictStrategy.IGNORE, contentProject);

                contentProject.put("id",2L);
                contentProject.put("name","Projet Lucidia");
                contentProject.put("color", 0xFFB4CDBA);
                db.insert("project_table",OnConflictStrategy.IGNORE, contentProject);

                contentProject.put("id",3L);
                contentProject.put("name","Projet Circus");
                contentProject.put("color", 0xFFA3CED2);
                db.insert("project_table",OnConflictStrategy.IGNORE, contentProject);

                contentTask.put("id", 1);
                contentTask.put("project_id", 1L);
                contentTask.put("name", "Tâche n° 1");
                contentTask.put("creationTimestamp", 1615297259000L);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentTask);

                contentTask.put("id", 2);
                contentTask.put("project_id", 2L);
                contentTask.put("name", "Tâche n° 2");
                contentTask.put("creationTimestamp", 1615297260000L);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentTask);

                contentTask.put("id", 3);
                contentTask.put("project_id", 3L);
                contentTask.put("name", "Tâche n° 3");
                contentTask.put("creationTimestamp", 1615297261000L);
                db.insert("task_table", OnConflictStrategy.IGNORE, contentTask);


            }
        };
    }
}

