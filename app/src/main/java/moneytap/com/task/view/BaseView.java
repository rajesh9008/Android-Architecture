package moneytap.com.task.view;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showDialog(String s);

    void setLoadingIndicator(boolean active);

}
