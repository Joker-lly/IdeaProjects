package Learning.arithmetic.greedy;

public class DP {

	public static void main(String[] args) {
		int value[] = {60,100,120};		//每个物品的钱
		int weight[] = {10,20,40};		//每个物品的重量 和上面的一一对应
		
		int w = 50;	//袋子的容积
		int n = 3;	//物品的个数
		int dp[][] = new int[n+1][w+1];		//表示分割，成一个 小的表格
		
		for(int i = 1; i<=n ;i++) {		//表示物品往里面加
			for(int cw = 1; cw <= w; cw ++) {	//袋子在每一个容积下所装的最大的钱
				if(weight[i - 1] <= cw) {		//表示这个物品可以装
					dp[i][cw] = Math.max(
							value[i-1]+dp[i-1][cw-weight[i-1]],		//我装新加的物品
							dp[i-1][cw]		//我不装这个新加的这个物品
					);
				}else {
					dp[i][cw] = dp[i-1][cw];		//新加的这个装不下 ，那么就取前一个物品装值
				}
			}
		}
		System.out.println("袋子能装的最大价值:" + dp[n][w]);
		
	}
}
