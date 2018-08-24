package moneytap.com.task.view.searchdetails;


import moneytap.com.task.presenter.BasePresenter;
import moneytap.com.task.view.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

    }

    interface Presenter extends BasePresenter {

    }
}
