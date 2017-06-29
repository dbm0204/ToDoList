package com.example.dbm0204.my_to_do_app;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class TaskDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_TASK = "task";
    TaskStorageHelper helper;
    private Task task;
    private List<Task> tasks = new ArrayList<>();
    private static int counter=0;
    EditText title;
    EditText description;
    CheckBox completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        // Get the task that we might have received
        Intent intent = getIntent();
        task = intent.getParcelableExtra(KEY_TASK);

        if (task != null) {
            // Add click listener only for an existing Task
            deleteButton.setOnClickListener(this);
        } else {
            // Don't show if task is null (new Task!)
            deleteButton.setVisibility(View.INVISIBLE);
        }
        // Only update the field if we have an existing task
        if (task != null) {
            title = (EditText) findViewById(R.id.title);
            title.setText(task.getTitle());
            description = (EditText) findViewById(R.id.description);
            description.setText(task.getDescription());
            completed = (CheckBox) findViewById(R.id.completed);
            completed.setChecked(task.isCompleted());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                saveOrCreateTask();
                break;
            case R.id.delete_button:
                deleteTask();
                break;
        }

    }
    private void deleteTask() {
        /**
         * TODO: implement logic to delete a task
         *
         */

    }
    private void saveOrCreateTask() {
        helper = new TaskStorageHelper();
        helper.InputTask(title.getText().toString(),description.getText().toString());

    }
}