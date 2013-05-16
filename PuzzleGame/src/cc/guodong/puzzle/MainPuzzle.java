package cc.guodong.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainPuzzle extends Activity {

	public static List<Bitmap> randomBitmap;
	List<Bitmap> piceceBitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_puzzle);
		//生成一个随机的Bitmap  list
		getRandomBitMap();
		PieceBitMapView myview = new PieceBitMapView(this);
		LinearLayout layout = (LinearLayout) super
				.findViewById(R.id.mainLinear);
		layout.addView(myview);

	}

	// 此方法用于产生一个随机的不重复的数组，从0-8;
	public static int[] myRandom() {

		int send[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int temp1, temp2, temp3;
		Random r = new Random();
		for (int i = 0; i < send.length; i++) // 随机交换send.length次
		{
			temp1 = Math.abs(r.nextInt()) % (send.length - 1); // 随机产生一个位置
			temp2 = Math.abs(r.nextInt()) % (send.length - 1); // 随机产生另一个位置
			if (temp1 != temp2) {
				temp3 = send[temp1];
				send[temp1] = send[temp2];
				send[temp2] = temp3;
			}
		}
		return send;
	}
	//该方法用于得到一个乱序的Bitmap数列
	public void getRandomBitMap()
	{
		// 根据R文件得到一个Bitmap对象
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.pic);
				// 对源图像进行一定程度的大小的调整
				Bitmap processedBitmap = Bitmap.createScaledBitmap(bitmap, 630, 600,
						true);
				// 该list用来存放切割后的图片
				piceceBitmap = new ArrayList<Bitmap>(9);

				// 每张小图片的宽度
				int width = processedBitmap.getWidth() / 3;
				// 每张小图片的高度
				int height = processedBitmap.getHeight() / 3;
				// x,y是每次切割左上角的坐标
				int x = 0;
				int y = 0;
				// 对大图片进行切割
				for (int i = 0; i < 3; i++) {

					for (int j = 0; j < 3; j++) {
						Bitmap tempBitmap = Bitmap.createBitmap(processedBitmap, x, y,
								width, height);
						// canvas.drawBitmap(tempBitmap, x, y, paint);
						x = x + width;
						piceceBitmap.add(tempBitmap);

					}
					y = y + height;
					x = 0;
				}
				piceceBitmap.remove(8);
				randomBitmap = new ArrayList<Bitmap>(9);
				int randomArray[] = new int[9];
				randomArray = myRandom();
				// 把图片list随机的付给另一个list
				for (int i = 0; i < 8; i++) {

					randomBitmap.add((Bitmap) piceceBitmap.get(randomArray[i]));
				}
				Bitmap nineBitmap = Bitmap.createBitmap(width, height,
						Bitmap.Config.ALPHA_8);
				randomBitmap.add(nineBitmap);
	}
}
