package moneytap.com.task.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import moneytap.com.task.R;
import moneytap.com.task.adapter.SearchAdapter;
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
    private SearchAdapter mListAdapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new SearchAdapter(getActivity(), new ArrayList<SearchedList.QueryBean.PagesBean>(0), mItemListener);
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
        mListView = (RecyclerView) getView().findViewById(R.id.rvItem);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mListAdapter);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(mListView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTasks(false);
            }
        });
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTasks(SearchedList tasks) {
        mListAdapter.replaceData(tasks.getQuery().getPages());
        mListView.setVisibility(View.VISIBLE);
        mNoTasksView.setVisibility(View.GONE);
    }

    @Override
    public void showDialog(String s) {
        showDialog(s);
    }

    @Override
    public void showSearchedDetailsUi(SearchedList.QueryBean.PagesBean requestedTask) {
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(EXTRA_TASK_ID, requestedTask);
        startActivity(intent);
    }
}
