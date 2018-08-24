package moneytap.com.task.net;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

import moneytap.com.task.model.SearchRequest;
import moneytap.com.task.model.SearchedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static moneytap.com.task.net.RestClient.getAPIInterface;

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;
    Map<String, SearchedList> mCachedTasks = new LinkedHashMap<>();

    private TasksRepository() {
    }

    public static TasksRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback, final SearchRequest searchRequest) {
        // Respond immediately with cache if available and not dirty
        if (mCachedTasks != null) {
            if (mCachedTasks.get(searchRequest.getSearchterm()) != null) {
                callback.onTasksLoaded(mCachedTasks.get(searchRequest.getSearchterm()));
                return;
            }
        }
        getAPIInterface().getResults(searchRequest.getAction(), searchRequest.getRequestType(),
                searchRequest.getSearchterm()).enqueue(new Callback<SearchedList>() {
            @Override
            public void onResponse(Call<SearchedList> call, Response<SearchedList> response) {
                mCachedTasks.put(searchRequest.getSearchterm(), (SearchedList) response.body());
                callback.onTasksLoaded((SearchedList) response.body());
            }

            @Override
            public void onFailure(Call<SearchedList> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }


    private void refreshCache(SearchedList tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
    }

}
