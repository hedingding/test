package com.test.gridviewchoicephoto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.test.R;

import java.io.File;

public class MainActivity extends Activity implements IViewEventListener {

    private ImageView iv;
    private MyImageGridView myImageGridView;
    private PopupWindow windowpic;
    private File photoFile;

    private static final int TAKE_PHOTO = 11;
    private static final int GET_PHOTO = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initpop();
        iv = (ImageView) findViewById(R.id.iv);
        myImageGridView = (MyImageGridView) findViewById(R.id.hlv);
        myImageGridView.setEventListener(this);
    }

    private void initpop() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow, null);

        windowpic = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        windowpic.setFocusable(true);

        ColorDrawable dw = new ColorDrawable(Color.parseColor("#40000000"));
        windowpic.setBackgroundDrawable(dw);

        windowpic.setAnimationStyle(R.style.mypopwindow_anim_style);

        Button b1 = (Button) view.findViewById(R.id.fromcamera);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                photoFile = new File(Environment.getExternalStorageDirectory()
                        + "/my_camera/fuck.jpg");
                if (!photoFile.getParentFile().exists()) {
                    photoFile.getParentFile().mkdirs();
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));

                startActivityForResult(intent, TAKE_PHOTO);

                windowpic.dismiss();
            }
        });

        Button b2 = (Button) view.findViewById(R.id.fromphoto);
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_PHOTO);
                windowpic.dismiss();
            }
        });

        Button b3 = (Button) view.findViewById(R.id.popup_cancel);
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                windowpic.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {// 如果拍照或者获取本地图片成功
            switch (requestCode) {
                case GET_PHOTO:
                    Uri uri = data.getData();

                    try {
                        iv.setImageBitmap(BitmapUtil.comp(MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                uri)));
                        myImageGridView.addimage(BitmapUtil.comp(MediaStore.Images.Media.getBitmap(this
                                        .getContentResolver(),
                                uri)));
                    } catch (Exception e) {
                    }
                    break;
                case TAKE_PHOTO:
                    myImageGridView.addimage(BitmapUtil.comp(BitmapFactory.decodeFile(photoFile.getPath())));
                    break;
            }
        } else {// 保存拍照或者获取本地图片失败
            new ToastManager(this).show("获取照片失败");
        }
    }

    @Override
    public void onUpdate(View paramView, int paramInt) {
        switch (paramInt) {
            case 1:
                windowpic.showAtLocation(findViewById(R.id.hlv),
                        Gravity.BOTTOM, 0, 0);

                break;

        }
    }
}
