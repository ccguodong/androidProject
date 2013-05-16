package cc.guodong.puzzle;

import android.graphics.Bitmap;

public class Piece {
	public Piece()
	{		
	}
	private int indexX;
	private int indexY;
	Bitmap bitMap;
	public int getIndexX() {
		return indexX;
	}
	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}
	public int getIndexY() {
		return indexY;
	}
	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}
	public Bitmap getBitMap() {
		return bitMap;
	}
	public void setBitMap(Bitmap bitMap) {
		this.bitMap = bitMap;
	}
	
}
