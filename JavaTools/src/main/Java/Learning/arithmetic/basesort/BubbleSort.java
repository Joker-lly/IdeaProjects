package Learning.arithmetic.basesort;

import java.util.Scanner;

public class BubbleSort {

	// 4 2 5 1
	// 4
	// 4 2 => 2 4
	// 2 4 5 =
	// 2 4 5 1=> 1 2 4 5

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);

		int n = cin.nextInt();
		int data[] = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = cin.nextInt();
		}
		// Map size() list.size();
		// n = 5
		//data 0 1 2 3 4 
		
		for (int i = 0, len = data.length; i < len - 1; i++) {
			for (int j = 0; j < len - 1 - i; j++) {
				if (data[j] > data[j + 1]) {
					// 交换 不让你引入第三个变量 交换 a b的值
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
		for(int i = 0 ; i < n; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

}
