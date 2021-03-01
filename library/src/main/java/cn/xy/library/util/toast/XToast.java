package cn.xy.library.util.toast;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import cn.xy.library.XApp;
import cn.xy.library.constant.ToastConstants;
import cn.xy.library.util.convert.XConvert;
import cn.xy.library.util.image.XImage;

/**
 * xiangy add by 12:48 2021/1/5
 *
 * DEMO
 * XToast.getInstance()
 *         .Text("AAAAAA")
 *         .TextColor(R.color.colorPrimary) //字体颜色
 *         .TextSize(50) //字体大小
 *         .BackGroundColor(R.color.colorAccent) //背景颜色
 *         .LayoutParamsX(0) //离X左边距离
 *         .LayoutParamsY(150) //离Y坐标距离
 *         .Gravity(Gravity.BOTTOM) //Toast显示位置的重心设置
 *         .RoundedCorners(3) //背景圆角大小
 *         .ShowTime(XToast.LENGTH_SHORTSHORT) //显示时长
 *         .show();
 */
public class XToast {
	private WindowManager mWindowManager;
	private LayoutParams mLayoutParams;
	private TextView mView;
	private final int HIDE = 1;
	private static XToast mToastView;
	private String Text = ToastConstants.Text;
	private int TextColor = ToastConstants.TextColor;
	private int BackGroundColor = ToastConstants.BackGroundColor;
	private float TextSize = ToastConstants.TextSize;
	private int Radius = ToastConstants.Radius;
	private int LayoutParamsX = ToastConstants.LayoutParamsX;
	private int LayoutParamsY = ToastConstants.LayoutParamsY;
	private int LayoutParamsGravity = ToastConstants.LayoutParamsGravity;
	private int DelayMillis = ToastConstants.LENGTH_SHORTSHORT_V;
	public static final int LENGTH_SHORTSHORT = ToastConstants.LENGTH_SHORTSHORT;
	public static final int LENGTH_SHORT = ToastConstants.LENGTH_SHORT;
	public static final int LENGTH_LONG = ToastConstants.LENGTH_LONG;
	public static final int LENGTH_LONGLONG = ToastConstants.LENGTH_LONGLONG;

	private XToast() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static XToast getInstance() {
		if (mToastView == null) {
			mToastView = new XToast(XApp.getApp());
			return mToastView;
		} else {
			return mToastView;
		}
	}

	public XToast Text(String toastString) {
		Text = toastString;
		return mToastView;
	}

	public XToast TextSize(int size){
		TextSize = size;
		return mToastView;
	}
	public XToast TextColor(int color){
		TextColor = color;
		return mToastView;
	}
	public XToast TextColor(String color){
		TextColor = XConvert.string2color(color);
		return mToastView;
	}

	public XToast RoundedCorners(int radius){
		Radius = radius;
		return mToastView;
	}
	public XToast BackGroundColor(int backgroundcolor){
		BackGroundColor = backgroundcolor;
		return mToastView;
	}
	public XToast BackGroundColor(String backgroundcolor){
		BackGroundColor = XConvert.string2color(backgroundcolor);
		return mToastView;
	}

	public XToast LayoutParamsX(int x){
		LayoutParamsX = x;
		return mToastView;
	}

	public XToast LayoutParamsY(int y){
		LayoutParamsY = y;
		return mToastView;
	}

	public XToast Gravity(int gravity){
		LayoutParamsGravity = gravity;
		return mToastView;
	}

	public XToast ShowTime(int i){
		switch (i){
			case LENGTH_SHORTSHORT:
				DelayMillis = ToastConstants.LENGTH_SHORTSHORT_V;
				break;
			case LENGTH_SHORT:
				DelayMillis = ToastConstants.LENGTH_SHORT_V;
				break;
			case LENGTH_LONG:
				DelayMillis = ToastConstants.LENGTH_LONG_V;
				break;
			case LENGTH_LONGLONG:
				DelayMillis = ToastConstants.LENGTH_LONGLONG_V;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + i);
		}
		return mToastView;
	}

	private XToast(Context context) {
		mView = new TextView(context);
		mWindowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		mLayoutParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 0, 0,
				LayoutParams.TYPE_PHONE,
				LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.RGBA_8888);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
		} else {
			mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
		}
	}

	public void show(){
		mHandler.post(runnable);
	}

	private void hide() {
		if (mView.getParent() != null) {
			mWindowManager.removeView(mView);
		}
	}

	private Handler mHandler = new Handler(Looper.getMainLooper()) {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == HIDE) {
				hide();
			}
		}
	};

	final Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (mView != null) {
				mView.setText(Text);
				mView.setTextColor(TextColor);
				mView.setPadding(8, 8, 8, 8);
				mView.setBackgroundColor(BackGroundColor);
				XImage.setRoundedCorners(mView,Radius);
				mView.setTextSize(TextSize);
			}
			mLayoutParams.gravity = LayoutParamsGravity;
			mLayoutParams.x = LayoutParamsX;
			mLayoutParams.y = LayoutParamsY;
			if (mView.getParent() == null) {
				mWindowManager.addView(mView, mLayoutParams);
			}
			mHandler.removeMessages(HIDE);
			mHandler.sendEmptyMessageDelayed(HIDE, DelayMillis);
		}
	};
}
