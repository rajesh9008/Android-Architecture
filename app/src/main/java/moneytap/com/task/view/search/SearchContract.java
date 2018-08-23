
package moneytap.com.task.view.search;

import android.support.annotation.NonNull;

import moneytap.com.task.model.SearchedList;
import moneytap.com.task.presenter.BasePresenter;
import moneytap.com.task.view.BaseView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showTasks(SearchedList tasks);

        void showSearchedDetailsUi(SearchedList.QueryBean.PagesBean requestedTask);
    }

    interface Presenter extends BasePresenter {

        void loadTasks(boolean forceUpdate);

        void openTaskDetails(@NonNull SearchedList.QueryBean.PagesBean requestedTask);


    }
}