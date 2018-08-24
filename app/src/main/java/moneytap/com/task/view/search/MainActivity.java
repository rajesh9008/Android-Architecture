package moneytap.com.task.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import moneytap.com.task.R;
import moneytap.com.task.net.SearchRepository;
import moneytap.com.task.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private SearchPresenter mSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchFragment tasksFragment =
                (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = SearchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        // Create the presenter
        mSearchPresenter = new SearchPresenter(
                SearchRepository.getInstance(), tasksFragment);
    }
}
