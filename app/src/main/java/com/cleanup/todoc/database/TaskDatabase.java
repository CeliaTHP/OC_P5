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
                            .addCallback(prepopulateDatabase()) //TODO when Executor set
                            .allowMainThreadQueries()
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
               // long id, long projectId, @NonNull String name, long creationTimestamp
                contentTask.put("id", 1);
                contentTask.put("project_id", 1);
                contentTask.put("name", "Ranger la salle");
                contentTask.put("creationTimestamp", "1612875202");

                db.insert("task_table", OnConflictStrategy.IGNORE, contentTask);
            }
        };
    }
}

