package com.test.updateapk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.test.R;
import com.test.gridviewchoicephoto.FlikerProgressBar;
import com.test.gridviewchoicephoto.LogUtils;
import com.test.gridviewchoicephoto.ToastManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class DownLoadApkDialog extends Dialog {
    private Context ctx;
    private TextView tip;
    private String url;
    private FlikerProgressBar pb;
    int fileSize;
    private int downLoadFileSize;
    private String filename;
    private int i = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//定义一个Handler，用于处理下载线程与UI间通讯
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 0:

                    case 1:
                        int result = downLoadFileSize * 100 / fileSize;
                        pb.setProgress(result);
                        break;
                    case 2:
                        dismiss();
                        LogUtils.e(Environment.getExternalStorageDirectory() + File.separator + "/update/" + filename);
                        openFile(new File(Environment.getExternalStorageDirectory() + File.separator + "/update/" +
                                filename));
                        break;

                    case -1:
                        String error = msg.getData().getString("error");
                        new ToastManager(ctx).show(error);
                        break;
                    case 3:
                        switch (i % 3) {
                            case 0:
                                tip.setText("正在下载.  ");
                                break;
                            case 1:
                                tip.setText("正在下载.. ");
                                break;
                            case 2:
                                tip.setText("正在下载...");
                                break;
                        }
                        break;
                }
            }
            super.handleMessage(msg);
        }
    };

    public DownLoadApkDialog(Context context, String url) {
        super(context,R.style.Dialog);
        this.ctx = context;
        this.url = url;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_dialog);
        pb = (FlikerProgressBar) findViewById(R.id.progressBar);
        tip = (TextView) findViewById(R.id.tip);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    sendMsg(3);
                    i++;
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    public void run() {
                        try {
                            //下载文件，参数：第一个URL，第二个存放路径
                            down_file(url, Environment.getExternalStorageDirectory() + File.separator +
                                    "/update/");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }).start();

    }


    public void down_file(String url, String path) throws IOException {
        //下载函数
        filename = url.substring(url.lastIndexOf("/") + 1);
        //获取文件名
        URL myURL = new URL(url);
        URLConnection conn = myURL.openConnection();
        conn.connect();
        InputStream is = conn.getInputStream();
        this.fileSize = conn.getContentLength();//根据响应获取文件大小
        if (this.fileSize <= 0) throw new RuntimeException("无法获知文件大小 ");
        if (is == null) throw new RuntimeException("stream is null");
        File file1 = new File(path);
        File file2 = new File(path + filename);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(path + filename);
        //把数据存入路径+文件名
        byte buf[] = new byte[1024];
        downLoadFileSize = 0;
        sendMsg(0);
        do {
            //循环读取
            int numread = is.read(buf);
            if (numread == -1) {
                break;
            }
            fos.write(buf, 0, numread);
            downLoadFileSize += numread;

            sendMsg(1);//更新进度条
        } while (true);

        sendMsg(2);//通知下载完成

        try {
            is.close();
        } catch (Exception ex) {
            Log.e("tag", "error: " + ex.getMessage(), ex);
        }

    }


    //在线程中向Handler发送文件的下载量，进行UI界面的更新
    private void sendMsg(int flag) {
        Message msg = new Message();
        msg.what = flag;
        handler.sendMessage(msg);
    }

    private void openFile(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        ctx.startActivity(intent);
    }


}
