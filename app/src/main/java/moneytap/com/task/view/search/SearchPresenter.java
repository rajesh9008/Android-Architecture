package moneytap.com.task.view.search;

import android.support.annotation.NonNull;

import moneytap.com.task.model.SearchRequest;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.net.SearchDataSource;
import moneytap.com.task.net.SearchRepository;
import moneytap.com.task.utils.Constants;


public class SearchPresenter implements SearchContract.Presenter {

    private final SearchRepository mSearchRepository;
    private final SearchContract.View mSearchView;
    private boolean mFirstLoad = true;

    public SearchPresenter(@NonNull SearchRepository searchRepository, @NonNull SearchContract.View searchView) {
        mSearchRepository = searchRepository;
        mSearchView = searchView;
        mSearchView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void openSearchDetails(@NonNull SearchedList.QueryBean.PagesBean pagesBean) {
        mSearchView.showSearchedDetailsUi(pagesBean);
    }

    @Override
    public void loadTasks(SearchRequest searchRequest) {
        mSearchView.setLoadingIndicator(true);
        mSearchRepository.getSearchItems(new SearchDataSource.LoadSearchedCallback() {
            @Override
            public void onTasksLoaded(SearchedList search) {
                mSearchView.setLoadingIndicator(false);
                mSearchView.showSearchedList(search);
            }

            @Override
            public void onDataNotAvailable() {
                mSearchView.showDialog(Constants.NETWORK_ERROR);
            }
        }, searchRequest);
    }

}
