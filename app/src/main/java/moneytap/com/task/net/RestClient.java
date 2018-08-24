package moneytap.com.task.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;

import java.io.File;
import java.util.concurrent.TimeUnit;

import moneytap.com.task.ApplicationDemo;
import moneytap.com.task.BuildConfig;
import moneytap.com.task.utils.Constants;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {

    private final static int cacheSize = 1024; // 1MB
    private static Retrofit retrofit;

    public static RestInterface getAPIInterface() {
        return getRestClient().create(RestInterface.class);
    }

    private static Retrofit getRestClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }


    private static OkHttpClient getClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //Http Cache
        File httpCacheDirectory = new File(ApplicationDemo.getAppContext().getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.cache(cache);

        builder.readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .connectionPool(new ConnectionPool(100, 5, TimeUnit.MINUTES))
                .retryOnConnectionFailure(true);

        //Debugging Interceptor
        builder.addInterceptor(new com.ihsanbal.logging.LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build());

        return builder.build();

    }


}