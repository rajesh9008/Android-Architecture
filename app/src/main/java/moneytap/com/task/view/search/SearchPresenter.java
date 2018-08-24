package moneytap.com.task.view.search;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import moneytap.com.task.model.SearchRequest;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.net.TasksDataSource;
import moneytap.com.task.net.TasksRepository;
import moneytap.com.task.utils.Constants;


public class SearchPresenter implements SearchContract.Presenter {

    private final TasksRepository mTasksRepository;
    private final SearchContract.View mTasksView;
    private boolean mFirstLoad = true;

    public SearchPresenter(@NonNull TasksRepository tasksRepository, @NonNull SearchContract.View tasksView) {
        mTasksRepository = tasksRepository;
        mTasksView = tasksView;
        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void openTaskDetails(@NonNull SearchedList.QueryBean.PagesBean requestedTask) {
        mTasksView.showSearchedDetailsUi(requestedTask);
    }

    @Override
    public void loadTasks(boolean forceUpdate, SearchRequest searchRequest) {
        // Simplification for sample: a network reload will be forced on first load.
        loadTasks(forceUpdate || mFirstLoad, true,searchRequest);
        mFirstLoad = false;
    }

    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI, SearchRequest searchRequest) {
        if (showLoadingUI) {
            mTasksView.setLoadingIndicator(true);
        }


        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(SearchedList tasks) {
                List<SearchedList> tasksToShow = new ArrayList<SearchedList>();
                if (showLoadingUI) {
                    mTasksView.setLoadingIndicator(false);
                    mTasksView.showTasks(tasks);
                }

            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showDialog(Constants.NETWORK_ERROR);
            }
        },searchRequest);
    }


}
