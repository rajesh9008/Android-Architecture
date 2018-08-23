package moneytap.com.task.view.search;

import moneytap.com.task.model.SearchedList;

public interface SearchItemListener {

        void onTaskClick(SearchedList.QueryBean.PagesBean clickedTask);

    }