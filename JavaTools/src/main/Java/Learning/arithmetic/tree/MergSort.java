package Learning.arithmetic.tree;

import java.util.Arrays;

public class MergSort {
	public static void main(String[] args) {
		int data[] = {9,5,6,8,0,3,7,1,20,1};
		mergeSort(data, 0, data.length -1);
		System.out.println(Arrays.toString(data));
		//String a = "1";
		//String b = "2";
		//a.compareTo(b);
	}
	
	public static void mergeSort(int data[],int left,int right) {
		if(left <  right) {
			int mid = (left + right) / 2;
			mergeSort(data, left, mid);
			mergeSort(data, mid+1, right);
			//以上就分完了
			merge(data, left, mid, right);
		}
	}
	
	public static void merge(int data[],int left,int mid,int right) {
		int temp[] = new int[data.length];		//就是用来保存合并后的序列，辅助我们排序
		
		int point1 = left;	//表示左边的第一个数的位置
		int point2 = mid + 1;	//表示后边的第一个数的位置
		
		int loc = left;	//这个就是用来保存我们当前填了那个数字到temp里面去
		while(point1 <= mid && point2 <= right) {
			if(data[point1] <= data[point2]) {
				temp[loc] = data[point1];
				loc ++ ;
				point1 ++ ;
			}else {
				temp[loc] = data[point2];
				loc ++ ;
				point2 ++ ;
			}
		}
		while(point1 <= mid) {
			temp[loc++] = data[point1++];
		}
		while(point2 <= right) {
			temp[loc ++] = data[point2 ++];
		}
		for(int i = left ;i <= right ; i++) {
			data[i] = temp[i];
		}
	}
	
}
