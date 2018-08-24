package moneytap.com.task.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import moneytap.com.task.R;
import moneytap.com.task.adapter.SearchAdapter;
import moneytap.com.task.model.SearchRequest;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.presenter.BasePresenter;
import moneytap.com.task.view.BaseFragment;
import moneytap.com.task.view.searchdetails.TaskDetailActivity;

import static moneytap.com.task.utils.Constants.EXTRA_TASK_ID;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private View mNoTasksView;
    private RecyclerView mListView;
    private SearchContract.Presenter mPresenter;
    SearchItemListener mItemListener = new SearchItemListener() {
        @Override
        public void onTaskClick(SearchedList.QueryBean.PagesBean clickedTask) {
            mPresenter.openTaskDetails(clickedTask);
        }

    };
    private ProgressBar mProgressBar;
    private SearchAdapter mListAdapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new SearchAdapter(new ArrayList<SearchedList.QueryBean.PagesBean>(0), mItemListener);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNoTasksView = getView().findViewById(R.id.noTasks);
        mProgressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        mListView = (RecyclerView) getView().findViewById(R.id.rvItem);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setHasFixedSize(true);
        mListView.setAdapter(mListAdapter);
        setHasOptionsMenu(true);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        if (active) {
            mProgressBar.setVisibility(View.VISIBLE);
            mNoTasksView.setVisibility(View.GONE);

        } else {
            mProgressBar.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    SearchRequest searchRequest = new SearchRequest();
                    searchRequest.setAction("query");
                    searchRequest.setSearchterm(newText);
                    searchRequest.setRequestType("json");
                    mPresenter.loadTasks(false, searchRequest);
                }
                return false;
            }
        });
    }

    @Override
    public void showTasks(SearchedList tasks) {
        if (tasks.getQuery() != null) {
            mListAdapter.replaceData(tasks.getQuery().getPages());
            mListView.setVisibility(View.VISIBLE);
            mNoTasksView.setVisibility(View.GONE);
        } else {
            mListView.setVisibility(View.GONE);
            mNoTasksView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDialog(String s) {
        showDialog(s);
    }

    @Override
    public void showSearchedDetailsUi(SearchedList.QueryBean.PagesBean requestedTask) {


        ActivityOptionsCompat transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), requestedTask.getPair());

        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(EXTRA_TASK_ID, requestedTask);
        ActivityCompat.startActivity(getActivity(), intent, transitionActivityOptions.toBundle());
    }
}
