package moneytap.com.task.view.searchdetails;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import moneytap.com.task.R;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.net.TasksRepository;
import moneytap.com.task.utils.ActivityUtils;

import static moneytap.com.task.utils.Constants.EXTRA_TASK_ID;


/**
 * Displays searched details screen.
 */
public class TaskDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Set up the toolbar.
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested task id
        SearchedList.QueryBean.PagesBean taskId = getIntent().getParcelableExtra(EXTRA_TASK_ID);

        TaskDetailFragment taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (taskDetailFragment == null) {
            taskDetailFragment = TaskDetailFragment.newInstance(taskId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.contentFrame);
        }

        // Create the presenter
        new TaskDetailPresenter(
                TasksRepository.getInstance(),
                taskDetailFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
