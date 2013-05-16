import java.util.Random;

public class MyRandom {

	public static void main(String[] args) {

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
		for (int i = 0; i < send.length; i++) {
			System.out.print(send[i]);
			System.out.print(",");
		}

	}
}