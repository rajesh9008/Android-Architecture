package moneytap.com.task.view.searchdetails;

import android.support.annotation.NonNull;

import moneytap.com.task.net.TasksRepository;


/**
 * Listens to user actions from the UI ({@link TaskDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final TaskDetailContract.View mTaskDetailView;

    public TaskDetailPresenter(@NonNull TasksRepository tasksRepository,
                               @NonNull TaskDetailContract.View taskDetailView) {
        mTasksRepository = tasksRepository;
        mTaskDetailView = taskDetailView;

        mTaskDetailView.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
