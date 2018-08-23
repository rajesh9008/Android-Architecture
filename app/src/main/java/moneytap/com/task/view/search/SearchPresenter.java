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

package moneytap.com.task.view.search;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

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
        loadTasks(false);
    }

    @Override
    public void openTaskDetails(@NonNull SearchedList.QueryBean.PagesBean requestedTask) {
        mTasksView.showSearchedDetailsUi(requestedTask);
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI) {
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
        });
    }


}
