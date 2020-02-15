package Learning.arithmetic.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Me implements Comparable<Me> {

	private int meNum; // 会议的编号
	private int startTime; // 会议开始时间
	private int endTime; // 会议结束时间

	public Me(int meNum, int startTime, int endTime) {
		this.setMeNum(meNum);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}

	@Override
	public int compareTo(Me o) {		//以结束时间排序 早结束的放前面
		if (this.endTime > o.endTime)
			return 1;
		return -1;
	}

	public int getMeNum() {
		return meNum;
	}

	public void setMeNum(int meNum) {
		this.meNum = meNum;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Me [meNum=" + meNum + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	

}

public class Meeting {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();	//有N个会议
		List<Me> mes = new ArrayList<>();
		for(int i = 0 ; i < n; i ++) {
			int start = cin.nextInt();
			int end = cin.nextInt();
			Me me = new Me(i+1, start, end);
			mes.add(me);
		}
		mes.sort(null);		//实现排序		这个排序其实就是 贪心的策略
		int curTime = 0 ;
		for(int i = 0;i < n; i ++) {
			Me me = mes.get(i);
			if(me.getStartTime() >= curTime) {	//判断能不能开
				System.out.println(me.toString());
				//
				curTime = me.getEndTime();
			}
		}
		
	}
	
	
}
