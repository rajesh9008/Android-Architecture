/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moneytap.com.task.net;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

import moneytap.com.task.model.SearchedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static moneytap.com.task.net.RestClient.getAPIInterface;

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;
    Map<String, SearchedList> mCachedTasks;
    boolean mCacheIsDirty = true;

    // Prevent direct instantiation.
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
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        // Respond immediately with cache if available and not dirty
        if (mCachedTasks != null && !mCacheIsDirty) {
          //  callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.


            getAPIInterface().getResults().enqueue(new Callback<SearchedList>() {
                @Override
                public void onResponse(Call<SearchedList> call, Response<SearchedList> response) {
                    refreshCache((SearchedList)response.body());
                    callback.onTasksLoaded((SearchedList)response.body());
                }

                @Override
                public void onFailure(Call<SearchedList> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });

        } else {
            // Query the local storage if available. If not, query the network.

        }
    }


    private void refreshCache(SearchedList tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        mCacheIsDirty = false;
    }

}
