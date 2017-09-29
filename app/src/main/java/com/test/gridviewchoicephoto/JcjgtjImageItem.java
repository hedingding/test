package com.test.gridviewchoicephoto;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test.R;

public class JcjgtjImageItem extends GenRelativeItem implements OnClickListener {
    private ImageView iv;
    private RelativeLayout ivdelete;
    private JcjgtjImage j;
    private IViewEventListener listener;

    public JcjgtjImageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onInit() {
        ivdelete = (RelativeLayout) findViewById(R.id.wxygl_jcjgtj_itemimagedelete);
        iv = (ImageView) findViewById(R.id.wxygl_jcjgtj_itemimage);
        iv.setOnClickListener(this);
        ivdelete.setOnClickListener(this);
    }

    protected void onSetData(Object o) {
        j = (JcjgtjImage) o;
        if (!j.isShowcha()) {
            iv.setImageResource(R.mipmap.fangkuangjia);
            iv.setBackgroundColor(Color.parseColor("#ffffff"));
            ivdelete.setVisibility(View.GONE);
        } else {
            ivdelete.setVisibility(View.VISIBLE);
            iv.setImageBitmap(j.getBitmap());
        }

    }

    @Override
    public void setViewCallback(IViewEventListener listener) {
        this.listener = listener;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wxygl_jcjgtj_itemimage:
                if (!j.isShowcha()) {
                    listener.onUpdate(v, 1);
                } else {

                }
                break;
            case R.id.wxygl_jcjgtj_itemimagedelete:
                v.setTag(j);
                listener.onUpdate(v, 2);
                break;
        }

    }
}
