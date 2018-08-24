package moneytap.com.task.view.searchdetails;

import android.support.annotation.NonNull;

import moneytap.com.task.net.SearchRepository;


/**
 * Listens to user actions from the UI ({@link SearchDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class SearchDetailPresenter implements SearchDetailContract.Presenter {


    public SearchDetailPresenter(@NonNull SearchRepository searchRepository,
                                 @NonNull SearchDetailContract.View detailView) {
        SearchDetailContract.View mSearchDetailView = detailView;
        mSearchDetailView.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
