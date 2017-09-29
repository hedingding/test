package com.test.testgif;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.R;

import java.io.FileInputStream;
import java.io.InputStream;

public class TestGifActivity extends Activity {
    private PlayGifTask mGifTask;
    ImageView iv;
    GifHelper.GifFrame[] frames;
    FileInputStream fis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iv = new ImageView(this);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        setContentView(iv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //对Gif图片进行解码
        final InputStream fis = getResources().openRawResource(R.drawable.gidpic);

        /*try {
            fis = new FileInputStream(new File(Environment.getExternalStorageDirectory()+File
            .separator+"/tmp/111.gif"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        frames = CommonUtil.getGif(fis);
        mGifTask = new PlayGifTask(iv, frames);
        mGifTask.startTask();
        Thread th = new Thread(mGifTask);
        th.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mGifTask) mGifTask.stopTask();

    }


    //用来循环播放Gif每帧图片
    private class PlayGifTask implements Runnable {
        int i = 0;
        ImageView iv;
        GifHelper.GifFrame[] frames;
        int framelen, oncePlayTime = 0;

        public PlayGifTask(ImageView iv, GifHelper.GifFrame[] frames) {
            this.iv = iv;
            this.frames = frames;

            int n = 0;
            framelen = frames.length;
            while (n < framelen) {
                oncePlayTime += frames[n].delay;
                n++;
            }
            Log.d("msg", "playTime= " + oncePlayTime);  //Gif鍥剧墖鍗曟鎾斁鏃堕暱

        }

        Handler h2 = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        iv.setImageBitmap((Bitmap) msg.obj);
                        break;
                }
            }

            ;
        };

        @Override
        public void run() {
            if (!frames[i].image.isRecycled()) {
                //      iv.setImageBitmap(frames[i].image);
                Message m = Message.obtain(h2, 1, frames[i].image);
                m.sendToTarget();
            }
            iv.postDelayed(this, frames[i++].delay);
            i %= framelen;
        }

        public void startTask() {
            iv.post(this);
        }

        public void stopTask() {
            if (null != iv) iv.removeCallbacks(this);
            iv = null;
            if (null != frames) {
                for (GifHelper.GifFrame frame : frames) {
                    if (frame.image != null && !frame.image.isRecycled()) {
                        frame.image.recycle();
                        frame.image = null;
                    }
                }
                frames = null;
                //      mGifTask=null;
            }
        }
    }

}
