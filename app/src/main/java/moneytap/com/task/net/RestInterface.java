package moneytap.com.task.net;

import moneytap.com.task.model.SearchedList;
import moneytap.com.task.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {


    @GET(Constants.sSEARCH_URL)
    Call<SearchedList> getResults();

}