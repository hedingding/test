package com.test.gridviewchoicephoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.test.R;

import java.util.ArrayList;

public class MyImageGridView extends GridView implements IViewEventListener {

    private ArrayList<JcjgtjImage> arraylist;
    private GenListviewAdapter adapter;
    private IViewEventListener listener;

    public MyImageGridView(Context context) {
        super(context);
    }


    public MyImageGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        adapter = new GenListviewAdapter(R.layout.h_image_item);
        arraylist = new ArrayList<JcjgtjImage>();
        JcjgtjImage j = new JcjgtjImage();
        j.setShowcha(false);
        arraylist.add(j);
        setAdapter(adapter);
        adapter.setViewEventListener(this);
        adapter.addAllItem(arraylist);
    }

    public final void setEventListener(IViewEventListener listener) {
        this.listener = listener;
    }

    public void addimage(Bitmap bitmap) {
        arraylist.remove(arraylist.size() - 1);
        JcjgtjImage j = new JcjgtjImage();
        j.setBitmap(bitmap);
        j.setShowcha(true);
        arraylist.add(j);
        if (arraylist.size() != 6) {
            JcjgtjImage j2 = new JcjgtjImage();
            j2.setShowcha(false);
            arraylist.add(j2);
        }
        adapter.clean();
        adapter.addAllItem(arraylist);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Bitmap> getbitmap() {
        ArrayList<Bitmap> arrayListBitmap = new ArrayList<Bitmap>();
        if (arraylist.size() == 1) {
            return null;
        } else {
            int q = 0;
            for (int ks = 0; ks < arraylist.size(); ks++) {
                if (arraylist.get(ks).isShowcha()) {
                    q++;
                }
            }
            if (q != 6) {
                arraylist.remove(arraylist.size() - 1);
            }
            for (int i = 0; i < arraylist.size(); i++) {
                arrayListBitmap.add(arraylist.get(i).getBitmap());
            }
            return arrayListBitmap;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    @Override
    public void onUpdate(View paramView, int paramInt) {
        switch (paramInt) {
            case 1:
                listener.onUpdate(paramView, 1);
                break;
            case 2:
                JcjgtjImage j = (JcjgtjImage) paramView.getTag();
                for (int i = 0; i < arraylist.size(); i++) {
                    if (j.getBitmap() == arraylist.get(i).getBitmap()) {
                        arraylist.remove(i);
                        break;
                    }
                }
                int i = 0;
                for (int k = 0; k < arraylist.size(); k++) {
                    if (arraylist.get(i).isShowcha()) {
                        i++;
                    }
                }
                if (i == 5) {
                    JcjgtjImage jo = new JcjgtjImage();
                    jo.setShowcha(false);
                    arraylist.add(jo);
                }
                adapter.clean();
                adapter.addAllItem(arraylist);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
