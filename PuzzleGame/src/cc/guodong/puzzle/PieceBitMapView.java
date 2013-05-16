package cc.guodong.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("DrawAllocation")
public class PieceBitMapView extends View {
	public static List<Bitmap> randomBitmap = new ArrayList<Bitmap>(9);
	Paint paint = new Paint();
	// ���������ڱ�ǿհ׵�ͼƬ
	int blankState;
	Piece piece[][] = new Piece[3][3];
	// �����õ�������
	float touchX;
	float touchY;

	public PieceBitMapView(Context context) {
		super(context);
		// ��MainPuzzle�����ɵ�������и�ֵ��randomBitmap
		for (int i = 0; i < MainPuzzle.randomBitmap.size(); i++) {
			randomBitmap.add((Bitmap) MainPuzzle.randomBitmap.get(i));
		}
		blankState = 8;

		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// ���Ƹ�ÿ��СͼƬ
		int width = ((Bitmap) randomBitmap.get(0)).getWidth();
		int height = ((Bitmap) randomBitmap.get(0)).getHeight();
		int indexX = 0;
		int indexY = 0;
		int count = 0;
		// ���Ƹ�view����������piece��ֵ��ͼƬ
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				canvas.drawBitmap((Bitmap) randomBitmap.get(count), indexX,
						indexY, paint);
				piece[i][j] = new Piece();
				piece[i][j].setIndexX(indexX);
				piece[i][j].setIndexY(indexY);
				piece[i][j].setBitMap((Bitmap) randomBitmap.get(count));
				indexX += width;
				count++;
			}
			indexY += height;
			indexX = 0;
		}

		Bitmap mybitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pic);
		Bitmap littleBitmap=Bitmap.createScaledBitmap(mybitmap, 350, 300, true);
		canvas.drawBitmap(littleBitmap, 200, 650, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		/*
		 * Log.v("-----y--",
		 * piece[getI(blankState)][getJ(blankState)].getIndexX() + "");
		 */
		this.touchX = event.getX();
		this.touchY = event.getY();
		if (touchX <= 630 & touchY <= 600) {
			// i,jΪ���ݴ���������꣬����ڵڼ��еڼ��У��������������ͼƬ
			int i = (int) (touchY / (((Bitmap) randomBitmap.get(0)).getHeight()));
			int j = (int) (touchX / (((Bitmap) randomBitmap.get(0)).getWidth()));
			/*
			 * Log.v("----i", i + ""); Log.v("----j", j + ""); Log.v("----n",
			 * getN(i, j) + ""); Log.v("blankX",
			 * piece[getI(blankState)][getJ(blankState)].getIndexX() + "");
			 * Log.v("touchX", piece[i][j].getIndexX() + ""); Log.v("blankY",
			 * piece[getI(blankState)][getJ(blankState)].getIndexY() + "");
			 * Log.v("touchY", piece[i][j].getIndexY() + "");
			 */
			int blankX = piece[getI(blankState)][getJ(blankState)].getIndexX();
			int blankY = piece[getI(blankState)][getJ(blankState)].getIndexY();
			int touchX = piece[i][j].getIndexX();
			int touchY = piece[i][j].getIndexY();
			if (((blankX - touchX == 0) && ((blankY - touchY == 200) || (blankY
					- touchY == -200)))
					|| (((blankY - touchY == 0) && ((blankX - touchX == 210) || (blankX
							- touchX == -210))))) {
				// �Կհ�ͼƬ�ʹ�����ͼƬ����λ�û���
				randomBitmap.set(blankState, piece[i][j].getBitMap());
				randomBitmap.set(getN(i, j),
						piece[getI(blankState)][getJ(blankState)].getBitMap());
				blankState = getN(i, j);
				this.invalidate();

			}
		}
		return super.onTouchEvent(event);
	}

	// �������������Ǹ��ݸ�����n�������ά�����ж�Ӧ��i��j
	public int getI(int n) {
		return n / 3;
	}

	public int getJ(int n) {
		if (n == 0 || n == 3 || n == 6) {
			return 0;
		} else if (n == 1 || n == 4 || n == 7) {
			return 1;
		} else if (n == 2 || n == 5 || n == 8) {
			return 2;
		} else
			return -1;
	}

	// �÷�������,������ά���������i��j�������ͼƬ��λ��n
	public int getN(int i, int j) {
		int n;
		if (i == 0) {
			n = 0 + j;
		} else if (i == 1) {
			n = 3 + j;
		} else if (i == 2) {
			n = 6 + j;
		} else
			n = 0;
		return n;
	}

}
