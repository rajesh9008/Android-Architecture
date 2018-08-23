package moneytap.com.task;

import android.app.Application;

public class ApplicationDemo extends Application {

    private static ApplicationDemo mApplicationDemo;

    public static ApplicationDemo getAppContext() {
        if(mApplicationDemo!=null){
            mApplicationDemo = new ApplicationDemo();
        }
        return mApplicationDemo;
    }
}
