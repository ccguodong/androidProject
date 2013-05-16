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
		//����һ�������Bitmap  list
		getRandomBitMap();
		PieceBitMapView myview = new PieceBitMapView(this);
		LinearLayout layout = (LinearLayout) super
				.findViewById(R.id.mainLinear);
		layout.addView(myview);

	}

	// �˷������ڲ���һ������Ĳ��ظ������飬��0-8;
	public static int[] myRandom() {

		int send[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int temp1, temp2, temp3;
		Random r = new Random();
		for (int i = 0; i < send.length; i++) // �������send.length��
		{
			temp1 = Math.abs(r.nextInt()) % (send.length - 1); // �������һ��λ��
			temp2 = Math.abs(r.nextInt()) % (send.length - 1); // ���������һ��λ��
			if (temp1 != temp2) {
				temp3 = send[temp1];
				send[temp1] = send[temp2];
				send[temp2] = temp3;
			}
		}
		return send;
	}
	//�÷������ڵõ�һ�������Bitmap����
	public void getRandomBitMap()
	{
		// ����R�ļ��õ�һ��Bitmap����
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.pic);
				// ��Դͼ�����һ���̶ȵĴ�С�ĵ���
				Bitmap processedBitmap = Bitmap.createScaledBitmap(bitmap, 630, 600,
						true);
				// ��list��������и���ͼƬ
				piceceBitmap = new ArrayList<Bitmap>(9);

				// ÿ��СͼƬ�Ŀ��
				int width = processedBitmap.getWidth() / 3;
				// ÿ��СͼƬ�ĸ߶�
				int height = processedBitmap.getHeight() / 3;
				// x,y��ÿ���и����Ͻǵ�����
				int x = 0;
				int y = 0;
				// �Դ�ͼƬ�����и�
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
				// ��ͼƬlist����ĸ�����һ��list
				for (int i = 0; i < 8; i++) {

					randomBitmap.add((Bitmap) piceceBitmap.get(randomArray[i]));
				}
				Bitmap nineBitmap = Bitmap.createBitmap(width, height,
						Bitmap.Config.ALPHA_8);
				randomBitmap.add(nineBitmap);
	}
}
