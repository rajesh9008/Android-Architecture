package moneytap.com.task.net;

import android.support.annotation.NonNull;

import moneytap.com.task.model.SearchRequest;
import moneytap.com.task.model.SearchedList;

public interface SearchDataSource {

    void getSearchItems(@NonNull LoadSearchedCallback callback, SearchRequest searchRequest);


    interface LoadSearchedCallback {

        void onTasksLoaded(SearchedList tasks);

        void onDataNotAvailable();
    }


}
