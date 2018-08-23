/*
 * Copyright (C) 2015 The Android Open Source Project
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

package moneytap.com.task.view.searchdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import moneytap.com.task.R;
import moneytap.com.task.presenter.BasePresenter;
import moneytap.com.task.view.BaseFragment;

/**
 * Main UI for the task detail screen.
 */
public class TaskDetailFragment extends BaseFragment implements TaskDetailContract.View {

    @NonNull
    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    @NonNull
    private static final int REQUEST_EDIT_TASK = 1;

    private TaskDetailContract.Presenter mPresenter;

    private TextView mDetailTitle;

    private TextView mDetailDescription;

    private CheckBox mDetailCompleteStatus;

    public static TaskDetailFragment newInstance(@Nullable String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TASK_ID, taskId);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(@NonNull TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_details;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return false;
    }


    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mDetailTitle.setText("");
            mDetailDescription.setText(getString(R.string.loading));
        }
    }


}
