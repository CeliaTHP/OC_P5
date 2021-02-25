package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TaskDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    //ProjectDAO
    private TaskDatabase database;
    private static long PROJECT_ID = 1;

    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Project 1", 0xFFB4CDBA);


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TaskDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetProject() throws InterruptedException {

        this.database.getProjectDao().createProject(PROJECT_DEMO);

        Project project = this.database.getProjectDao().getProject(PROJECT_ID);
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
    }


    //TaskDAO
    private static Task TASK_1 = new Task(0,PROJECT_ID,"Fermer les fenêtres" ,1612642375);
    private static Task TASK_2 = new Task(0,PROJECT_ID,"Nourrir le chien" ,1612642435);
    private static Task TASK_3 = new Task(0,PROJECT_ID,"Fermer les salles" ,1612642465);


    @Test
    public void getTasksWhenNoTasksInserted() throws InterruptedException {

        List<Task> tasks = LiveDataTestUtil.getValue(this.database.getTaskDao().getTasksForProject(PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        this.database.getProjectDao().createProject(PROJECT_DEMO);
        this.database.getTaskDao().createTask(TASK_1);
        this.database.getTaskDao().createTask(TASK_2);
        this.database.getTaskDao().createTask(TASK_3);

        List<Task> tasks = LiveDataTestUtil.getValue(this.database.getTaskDao().getTasksForProject(PROJECT_ID));
        assertEquals(3, tasks.size());
    }

    @Test
    public void insertAndUpdateItem() throws InterruptedException {
        // BEFORE : Adding demo project & demo tasks. Next, update task added & re-save it
        this.database.getProjectDao().createProject(PROJECT_DEMO);
        this.database.getTaskDao().createTask(TASK_1);
        Task taskAdded = LiveDataTestUtil.getValue(this.database.getTaskDao().getTasksForProject(PROJECT_ID)).get(0);
        taskAdded.setName("Fermer les volets");
        this.database.getTaskDao().updateTask(taskAdded);
        //TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.getTaskDao().getTasksForProject(PROJECT_ID));
        assertTrue(tasks.size() == 1 && tasks.get(0).getName().equals("Fermer les volets"));
    }

    @Test
    public void insertAndDeleteItem() throws InterruptedException {
        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.
        this.database.getProjectDao().createProject(PROJECT_DEMO);
        this.database.getTaskDao().createTask(TASK_1);
        Task taskAdded = LiveDataTestUtil.getValue(this.database.getTaskDao().getTasksForProject(PROJECT_ID)).get(0);
        this.database.getTaskDao().deleteTask(taskAdded.getId());

        //TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.getTaskDao().getTasksForProject(PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }

    //readItem







}
