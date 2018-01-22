package util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import base.MlApplication;

/**
 * Created by zhongwang on 2018/1/19.
 */

public class MlConnectUtil {
    /**
     * 端口号  默认888可以通关get set方法修改
     */
    private static int port = 8888;
    private static BufferedReader bufferedReader;
    private static ExecutorService threadPool;
    private static MlApplication mlApplication;
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public MlConnectUtil(MlApplication application) {
        mlApplication = application;
        Log.d("ZWW",mlApplication.isConnected()+" sssss");
    }

    private static Socket clientSocket;

    /**
     * 基于socket通信连接电机
     *
     * @param ipAddress ip地址
     */
    public static void ConnectServer(final String ipAddress, final MlApplication mlApplication) {
        threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                    clientSocket = new Socket(ipAddress, port);
                    Log.d("zww","开始测试连接");
                    bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String res = bufferedReader.readLine();
                    mlApplication.setConnected(res.equals(MlConCommonUtil.CONNECTSUCESS));
                    Log.d("zww",res);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("zww", "clientSocket Create faulure");
                }
            }
        });
    }

}
