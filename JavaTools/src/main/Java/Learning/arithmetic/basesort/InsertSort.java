package Learning.arithmetic.basesort;

import java.util.Scanner;

/**
 * 插入排序
 */
public class InsertSort {

	public static void main(String[] args) {
		// 4 2 3 1
		// 第一步：    4
		// 第二步:  2 4
		// 第三步:  2 3 4
		// 第四部步: 1 2 3 4
		Scanner cin = new Scanner(System.in);
		
		int n = cin.nextInt();//输入数组大小
		int data[] = new int[n+1];
		for(int i = 0 ; i < n; i ++) {
			data[i] = cin.nextInt();//循环输入数组中的数组
		}
		
		for(int i = 1 ; i < n; i++) {	//每次加的数进来
			for(int j = i; j > 0 ; j--) {	//这个j表示的是已经排好序的序列
				if(data[j] < data[j - 1]) {
					//交换 不让你引入第三个变量 交换 a b的值
					int temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				}else {
					break;
				}
			}
		}
		for(int i = 0 ; i < n; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
}

// a = 2 ,b = 3 要你交换 a b的值，补鞥呢引入第三个变量 只要用到 加减就可以。 也是一个同花顺的面试考题
// a = a + b; => a = 5
// b = a - b; => b = 5 - 3 = 2
// a = a - b; => a = 5 - 2 = 3
