package Learning.arithmetic.basesort;

import java.util.Scanner;

public class ShellSort {

	public static void main(String[] args) {
		// 4 1 2 3 5
		// 第一步
		//len = 5 表示有5个数字
		// step = len /2 => 5/2 =2;
		// 4 2 =>分出来的还是进行一个插入排序 2 4
		// 1 3 => 1 3
		// 5=> 5
		// 2 4 1 3 5
//100 / 2 = 50
// 50 / 2 => 25
//25 /2 
//step = 1排完
		// 第二步 step = step / 2 =>1

		// 第四部步: 1 2 3 4
		Scanner cin = new Scanner(System.in);

		int n = cin.nextInt();
		int data[] = new int[n + 1];
		for (int i = 0; i < n; i++) {
			data[i] = cin.nextInt();
		}
		
		int step = n;
		while(step >= 1) {
			step = step / 2;
			for(int i = step ; i < n; i ++) {
				for(int j = i ;j - step >= 0; j-= step) {
					if(data[j] < data[j - step]) {
						int temp = data[j];
						data[j] = data[j - step];
						data[j - step] = temp;
					}else {
						break;
					}
				}
			}
		}
		
		for(int i = 0 ; i < n; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();

	}
}
