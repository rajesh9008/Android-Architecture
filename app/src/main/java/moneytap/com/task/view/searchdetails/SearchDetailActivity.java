package moneytap.com.task.view.searchdetails;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import moneytap.com.task.R;
import moneytap.com.task.model.SearchedList;
import moneytap.com.task.net.SearchRepository;
import moneytap.com.task.utils.ActivityUtils;

import static moneytap.com.task.utils.Constants.EXTRA_TASK_ID;


/**
 * Displays searched details screen.
 */
public class SearchDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Set up the toolbar.
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        SearchedList.QueryBean.PagesBean pagesBean = getIntent().getParcelableExtra(EXTRA_TASK_ID);

        SearchDetailFragment searchDetailFragment = (SearchDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (searchDetailFragment == null) {
            searchDetailFragment = SearchDetailFragment.newInstance(pagesBean);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    searchDetailFragment, R.id.contentFrame);
        }

        // Create the presenter
        new SearchDetailPresenter(
                SearchRepository.getInstance(),
                searchDetailFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
