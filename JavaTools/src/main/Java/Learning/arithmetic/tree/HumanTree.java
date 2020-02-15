package Learning.arithmetic.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class MyNode implements Comparable<MyNode>{
	
	private String chars = "";		//存的数据
	private int weight;		//权重值
	private MyNode leftNode;
	private MyNode rightNode;
	private MyNode parent;
	
	@Override
	public int compareTo(MyNode o) {
		return this.getWeight() - o.getWeight();
	}


	public String getChars() {
		return chars;
	}


	public void setChars(String chars) {
		this.chars = chars;
	}


	public MyNode getLeftNode() {
		return leftNode;
	}


	public void setLeftNode(MyNode leftNode) {
		this.leftNode = leftNode;
	}


	public MyNode getRightNode() {
		return rightNode;
	}


	public void setRightNode(MyNode rightNode) {
		this.rightNode = rightNode;
	}


	public MyNode getParent() {
		return parent;
	}


	public void setParent(MyNode parent) {
		this.parent = parent;
	}
	//优先队列


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}
}

public class HumanTree {
	
	private MyNode root;
	private List<MyNode> leafs;		//叶子节点
	Map<Character, Integer> weights;	//每个叶子节点的权重
	public HumanTree(Map<Character, Integer> weights) {
		this.leafs = new ArrayList<>();		
		this.weights = weights;
	}
	
	private Map<Character, String> codeInfo(){	//打印出编码，其实就是树的遍历
		
		Map<Character, String> map = new HashMap<>();
		for(MyNode node : leafs) {		//我这里是从叶子节点开始的，你也可以从root节点开始找
			Character c = new Character(node.getChars().charAt(0));		//取出我们叶子节点存的那个字符 叶子节点只有一个值
			String code = "";	//记录编码
			MyNode curNode = node;	//从这个点开始找
			do {
				//我们要判断当前这个点是在左边还是右边，因为左边是0 右边是1
				if(curNode.getParent() != null && curNode.getParent().getLeftNode() == curNode) {		//表示在左边
					code = "0" + code;
				}else {
					code = "1" + code;
				}
				curNode = curNode.getParent();
			}while(curNode.getParent() != null);		//从叶子节点往前面找 一直找到根节点，即parent没有了
			map.put(c, code);
		}
		return map;
	}
	
	public void decode() {		//解码
		
	}
	
	private void creatTree() {
		PriorityQueue<MyNode> priorityQueue = new PriorityQueue<>();	//优先队列用来排序的
		Character keys[] = weights.keySet().toArray(new Character[0]);
		for(Character c : keys) {		//把所有的权重拿出来放到优先队列里面去
			MyNode node = new MyNode();
			node.setChars(c.toString());
			node.setWeight(weights.get(c));
			priorityQueue.add(node);
			leafs.add(node);
		}
		//取优先队列的数据进行合并
		int len = priorityQueue.size();
		for(int i = 0 ;i < len - 1 ; i++) {		// 长度为n就要合并n-1次
			MyNode node1 = priorityQueue.poll();	//出队列
			MyNode node2 = priorityQueue.poll();
			//以上两个就一定是队列里面最小的两个数
			//以下就是合并一个node的过程
			MyNode newNode = new MyNode();
			newNode.setChars(node1.getChars() + node2.getChars());
			newNode.setWeight(node1.getWeight() + node2.getWeight());
			newNode.setLeftNode(node1);
			newNode.setRightNode(node2);
			node1.setParent(newNode);
			node2.setParent(newNode);
			priorityQueue.add(newNode);
		}
		root = priorityQueue.poll();
		System.out.println("创建完成");
		
	}
	public static void main(String[] args) {
		Map<Character, Integer> weightMap = new HashMap<>();
		//这个就是构建加密树 只要随便动一下 整个就不一样了,往往以某本书的字出现了多少次作为权重map
		weightMap.put('A', 7);
		weightMap.put('B', 0);
		weightMap.put('C', 2);
		weightMap.put('D', 4);
		
		HumanTree humanTree = new HumanTree(weightMap);
		humanTree.creatTree();
		Map<Character, String> map = humanTree.codeInfo();
		System.out.println("A:"+map.get('A'));
		System.out.println("B:"+map.get('B'));
		System.out.println("C:"+map.get('C'));
		System.out.println("D:"+map.get('D'));
		
		/*
		 * 
A:1
B:000
C:001
D:01
001
		 */
		System.out.println("AB的密文:"+(map.get('A')+map.get('B')));
	}
	
	
}
