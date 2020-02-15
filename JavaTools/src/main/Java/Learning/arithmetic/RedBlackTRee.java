package Learning.arithmetic;

import org.springframework.beans.factory.support.ManagedProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RedBlackTRee {
	/**
	 * 1每个节点不是红色就是黑色
	 * 2.不可能有连在一起的红色
	 * 3.根节点都是黑色
	 * 4。每个红色的节点的两个子节点都是黑色
	 * 5.任意一节点到其子树中每个叶子节点的路径都有相同数量的黑色节点
	 * 左旋：
	 *
	 * 旋转和变颜色的规则：
	 * 1.变颜色的情况：当前节点的父节点是红色，且其祖父节点的另一个子节点也是红色，需要变
	 * 		（1）把父节点设为黑色，
	 * 		（2）把叔叔节点设为黑色
	 * 		（3）把祖父节点设为红色
	 * 		（4）把指针定义到祖父节点
	 * 2.左旋：
	 * 		（1）当前父节点是红色，叔叔是黑色，且当前的节点是右子树以父节点左旋
	 * 3.右旋：
	 * 		（1）当前父节点是红色，叔叔是黑色，且当前的节点是右左树 以父节点左旋
	 * 		（2）把父节点变为黑色
	 * 		（3）把祖父节点变红色
	 * 		（4）以祖父节点旋转
	 */
	public static void main(String[] args) {
		
	}
}
