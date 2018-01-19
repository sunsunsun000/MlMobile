package base;

import android.app.Application;

/**
 * Created by zhongwang on 2018/1/9.
 */

public class MlApplication extends Application {
    private boolean isConnected = true;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2018/1/9 通过wifi链接机器 
    }
}
