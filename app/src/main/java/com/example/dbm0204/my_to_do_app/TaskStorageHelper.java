package com.example.dbm0204.my_to_do_app;

/**
 * Created by dbm0204 on 6/29/17.
 */
import android.content.Context;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
public final class TaskStorageHelper {
    static final TaskStorageHelper INSTANCE = new TaskStorageHelper();
    List<Task> tasks = new ArrayList<>();
    static int counter;
    TaskStorageHelper() {}

    public TaskStorageHelper(List<Task> tasks) {
        this.tasks = tasks;
        this.counter=0;
    }

    public void initStorage(Context context){
        Task first = new Task();
        first.setId(counter++);
        first.setTitle("Buy Apple");
        first.setDescription("But Apples for Mother ");
        tasks.add(first);
        Task second = new Task();
        second.setId(counter++);
        second.setTitle("Buy Medicine");
        second.setDescription("Buy Medicine that Doctor ordered");
        tasks.add(second);


    }
    public void InputTask(String mTitle, String mDesc){
        Task items= new Task();
        items.setId(counter++);
        items.setTitle(mTitle);
        items.setDescription(mDesc);
        tasks.add(items);

    }
    public static TaskStorageHelper getInstance() {
        return INSTANCE;
    }
    public void saveTask(Task task) {
        long nextId = 0;
        for (Task existingTask : tasks) {
            nextId = existingTask.getId() > nextId ? existingTask.getId() : nextId;
            if (task.getId() == existingTask.getId()) {
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setCompleted(task.isCompleted());
                existingTask.setArchived(task.isArchived());
                return;
            }
        }
        nextId++;
        task.setId(nextId);
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
