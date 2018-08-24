package moneytap.com.task;

import android.app.Application;

public class ApplicationDemo extends Application {

    private static ApplicationDemo mApplicationDemo;

    public static ApplicationDemo getAppContext() {
        return mApplicationDemo;
    }

    @Override
    public void onCreate() {
        mApplicationDemo = this;
        super.onCreate();

    }
}
