package util;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import base.MlApplication;
import base.SocketModule;

/**
 * Created by zhongwang on 2018/1/19.
 */

public class MlConnectUtil {
    /**
     * 端口号  默认888可以通关get set方法修改
     */
    private  int port = 8888;
    /**
     * 与电机之间通信 获取数据
     */
    private  BufferedReader bufferedReader;
    /**
     * 与电机之间通信 发送指令
     */
    private  PrintWriter printWriter;
    private  InputStream inputStream;
    private  ExecutorService threadPool;
    private  MlApplication mlApplication;
    private static  MlConnectUtil mlConnectUtil;
    private Gson gson;
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public MlConnectUtil(MlApplication application) {
        mlApplication = application;
        gson = mlApplication.getGson();
        Log.d("ZWW",mlApplication.isConnected()+" sssss");
    }

    public static MlConnectUtil getMlConnectUtil(MlApplication application){
        if(mlConnectUtil==null)
            mlConnectUtil = new MlConnectUtil(application);
                    return mlConnectUtil;
    }
    private  Socket clientSocket;

    public  BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public  void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public  PrintWriter getPrintWriter() {
        return printWriter;
    }

    public  void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
    public  void getDataFromMotor(final SocketModule socketModule, final OperateData operateData){
        Log.d("zww","数据填充完成，准备发送至服务端");
        new Thread(){
            @Override
            public void run() {
                super.run();
              printWriter.println(mlApplication.getGson().toJson(socketModule));
                startObserver(operateData);
            }
        }.start();
    }

    private void startObserver(OperateData operateData) {
        try {
        while(true){
            if(inputStream!=null&inputStream.available()!=0){
              String moduleRes=  bufferedReader.readLine();
              Log.d("zww","接收到来自服务器的响应 内容如下"+moduleRes);
              SocketModule socketModule = gson.fromJson(moduleRes,SocketModule.class);
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 基于socket通信连接电机
     *
     * @param ipAddress ip地址
     */
    public  void ConnectServer(final String ipAddress, final MlApplication mlApplication) {
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
                    inputStream  = clientSocket.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String res = bufferedReader.readLine();
                    mlApplication.setConnected(res.equals(MlConCommonUtil.CONNECTSUCESS));
                    if(mlApplication.isConnected()){
                        printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
                    }
                    Log.d("zww",res);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("zww", "clientSocket Create faulure");
                    if(printWriter!=null)
                        printWriter.close();
                    if(bufferedReader!=null)
                        try {
                            bufferedReader.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                }
            }
        });
    }
    public interface OperateData{
       void handlerData(SocketModule socketModule);
    }
}
