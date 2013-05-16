package cc.guodong.testdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
Bitmap bitmap;
Paint paint=new Paint();
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pic);
		canvas.drawBitmap(bitmap, 0, 0, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		return super.onTouchEvent(event);
	}

}
