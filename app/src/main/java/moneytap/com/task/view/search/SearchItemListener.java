package moneytap.com.task.view.search;

import moneytap.com.task.model.SearchedList;

public interface SearchItemListener {

    void onSearchedItemClick(SearchedList.QueryBean.PagesBean clickedPageBean);

}