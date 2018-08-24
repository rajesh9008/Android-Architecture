package moneytap.com.task.view.searchdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import moneytap.com.task.R;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.presenter.BasePresenter;
import moneytap.com.task.view.BaseFragment;

/**
 * Main UI for the searched detail screen.
 */
public class TaskDetailFragment extends BaseFragment implements TaskDetailContract.View {

    private static final String ARGUMENT_TASK_ID = "TASK_ID";
    private TaskDetailContract.Presenter mPresenter;
    private SearchedList.QueryBean.PagesBean mPagesBean;

    public static TaskDetailFragment newInstance(@Nullable SearchedList.QueryBean.PagesBean taskId) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_TASK_ID, taskId);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagesBean = getArguments().getParcelable(ARGUMENT_TASK_ID);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPagesBean != null) {
            ImageView imageView = getView().findViewById(R.id.imageView);
            TextView title = getView().findViewById(R.id.textViewTitle);
            TextView desc = getView().findViewById(R.id.textViewDesc);
            if (mPagesBean.getThumbnail() != null && mPagesBean.getThumbnail().getSource() != null && mPagesBean.getThumbnail().getSource().length() > 0)
                Picasso.get().load(mPagesBean.getThumbnail().getSource()).placeholder(R.mipmap.ic_launcher).into(imageView);

            if (mPagesBean.getTitle() != null)
                title.setText(mPagesBean.getTitle());
            StringBuilder stringBuilder = new StringBuilder();
            if (mPagesBean.getTerms() != null && mPagesBean.getTerms().getDescription() != null)
                for (String s : mPagesBean.getTerms().getDescription()) {
                    stringBuilder.append(s);
                    stringBuilder.append("\n");
                }
            desc.setText(stringBuilder.toString());
        }
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
    public void setLoadingIndicator(boolean active) {

    }
}
