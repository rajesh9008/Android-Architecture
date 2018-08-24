package moneytap.com.task.net;

import android.support.annotation.NonNull;

import moneytap.com.task.model.SearchRequest;
import moneytap.com.task.model.SearchedList;

public interface TasksDataSource {

    void getTasks(@NonNull LoadTasksCallback callback, SearchRequest searchRequest);


    interface LoadTasksCallback {

        void onTasksLoaded(SearchedList tasks);

        void onDataNotAvailable();
    }


}
