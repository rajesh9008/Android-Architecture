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

public class SearchRepository implements SearchDataSource {

    private static SearchRepository INSTANCE = null;
    Map<String, SearchedList> mCachedSearchedItems = new LinkedHashMap<>();

    private SearchRepository() {
    }

    public static SearchRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SearchRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getSearchItems(@NonNull final LoadSearchedCallback callback, final SearchRequest searchRequest) {
        // Respond immediately with cache if available and not dirty
        if (mCachedSearchedItems != null) {
            if (mCachedSearchedItems.get(searchRequest.getSearchterm()) != null) {
                callback.onTasksLoaded(mCachedSearchedItems.get(searchRequest.getSearchterm()));
                return;
            }
        }
        getAPIInterface().getResults(searchRequest.getAction(), searchRequest.getRequestType(),
                searchRequest.getSearchterm()).enqueue(new Callback<SearchedList>() {
            @Override
            public void onResponse(Call<SearchedList> call, Response<SearchedList> response) {
                mCachedSearchedItems.put(searchRequest.getSearchterm(), (SearchedList) response.body());
                callback.onTasksLoaded((SearchedList) response.body());
            }

            @Override
            public void onFailure(Call<SearchedList> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }


    private void refreshCache(SearchedList list) {
        if (mCachedSearchedItems == null) {
            mCachedSearchedItems = new LinkedHashMap<>();
        }
        mCachedSearchedItems.clear();
    }

}
