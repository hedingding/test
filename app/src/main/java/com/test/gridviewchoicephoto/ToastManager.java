package com.test.gridviewchoicephoto;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastManager {

	private static Toast mToastLast;
	private static int mResIdLast;
	private static String mTextLast;
	private static long mShowTimeLast;

	private Context mContext;
	private final Handler mHandler;

	public ToastManager(Context context) {
		mContext = context;
		mHandler = new Handler(Looper.getMainLooper());
	}

	private Runnable mRunnable = new Runnable() {

		@Override
		public void run() {
			mToastLast = Toast.makeText(mContext, mResIdLast,
					Toast.LENGTH_SHORT);
			// mToastLast.setGravity(Gravity.CENTER, 0, 0);
			mToastLast.show();
			mShowTimeLast = System.currentTimeMillis();
		}
	};

	private Runnable mRunnableText = new Runnable() {

		@Override
		public void run() {
			mToastLast = Toast
					.makeText(mContext, mTextLast, Toast.LENGTH_SHORT);
			// mToastLast.setGravity(Gravity.BOTTOM, 0, 0);
			mToastLast.show();
			mShowTimeLast = System.currentTimeMillis();
		}
	};

	public void show(int nResId) {
		if (nResId == mResIdLast) {
			if (System.currentTimeMillis() - mShowTimeLast < 2000) {
				return;
			}
		}
		if (mToastLast != null) {
			mToastLast.cancel();
		}

		mResIdLast = nResId;
		mHandler.removeCallbacks(mRunnable);
		mHandler.post(mRunnable);
	}

	public void show(final String strText) {
		if (TextUtils.isEmpty(strText)) {
			return;
		}

		if (strText.equals(mTextLast)) {
			if (System.currentTimeMillis() - mShowTimeLast < 2000) {
				return;
			}
		}

		mTextLast = strText;
		mHandler.removeCallbacks(mRunnableText);
		mHandler.post(mRunnableText);
	}
}
