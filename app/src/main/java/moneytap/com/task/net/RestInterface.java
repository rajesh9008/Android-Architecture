package moneytap.com.task.net;

import moneytap.com.task.model.SearchedList;
import moneytap.com.task.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {


    @GET(Constants.sSEARCH_URL)
    Call<SearchedList> getResults(
            @Query("action") String action,
            @Query("format") String format,
            @Query("gpssearch") String gpssearch);

}