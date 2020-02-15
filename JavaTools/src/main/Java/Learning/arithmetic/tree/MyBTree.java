package Learning.arithmetic.tree;

class BTreeNode {
	int T;		//表示每个节点最少的值个数 m/2
	int count;	//节点值个数
	int key[];	//关键字个数 数据
	BTreeNode childs[];	//指针个数
	Boolean isleaf;	//叶子节点

	public BTreeNode(int T) {
		this.T = T;
		key = new int[2 * T - 1];
		childs = new BTreeNode[2 * T];
		isleaf = true;
		count = 0;
	}
}

public class MyBTree {

	int M;
	BTreeNode root;

	public MyBTree(int order) {
		this.M = order;
		root = new BTreeNode(order);
	}

	public BTreeNode search(BTreeNode root, int key) {
		int i = 0;
		while (i < root.count && key > root.key[i]) {
			i++;
		}

		if (i <= root.count && key == root.key[i]) {
			return root;
		}
		if (root.isleaf) {		//搜到根节点了直接返回null
			return null;
		} else {		//继续搜下一层
			return search(root.childs[i], key);
		}
	}
	public void insert(MyBTree tree, int key) {
		BTreeNode r = tree.root;
		if (r.count == 2 * M - 1) {		//判断根节点是否已经填满
			BTreeNode newNode = new BTreeNode(M);
			tree.root = newNode;
			newNode.childs[0] = r;		//根节点分裂成一个新的，将第一个子节点指向原来的root节点,并将左边赋给第一个子节点
			newNode.isleaf = false;
			newNode.count = 0;
			split(newNode, 0, r);		//进行分裂操作
			fillNodeIn(newNode, key);	//分裂完成后插入数据
		} else{
			fillNodeIn(r, key);
		}
	}

	//核心部分就是这个分裂，最容易出问题,我在这里也卡了很久,spiltNode 为需要分裂的点 newNode分裂后的结果
	public void split(BTreeNode newNode, int i, BTreeNode spiltNode) {
		BTreeNode tempNode = new BTreeNode(M);		//分裂节点的临时存储数据节点，存储分裂节点右边数据和右子树的数据
		tempNode.isleaf = spiltNode.isleaf;
		tempNode.count = M - 1;
		for (int j = 0; j < M - 1; j++) {
			tempNode.key[j] = spiltNode.key[j + M];		//把分裂的节点右边数据先赋值到临时节点
		}
		if (!spiltNode.isleaf) {
			for (int k = 0; k < M; k++) {		//如果分裂节点不是叶子节点则要把分裂节点的右子树全部赋值给临时节点
				tempNode.childs[k] = spiltNode.childs[k + M];
			}
		}
		spiltNode.count = M - 1;
		for (int j = newNode.count; j > i; j--) {	//源节点数据进行平移
			newNode.childs[j + 1] = newNode.childs[j];
		}
		newNode.childs[i + 1] = tempNode;		//将临时存储了数据的节点插入源节点

		for (int j = newNode.count; j >= i && j < newNode.key.length - 1; j--) {
			newNode.key[j + 1] = newNode.key[j];
		}
		newNode.key[i] = spiltNode.key[M - 1];	//将分裂出来的那个key插入到源节点去
		spiltNode.key[M - 1] = 0;			//分裂节点分出去的key重置
		for (int j = 0; j < M -1 ; j++) {	//分裂节点右边key重置
			spiltNode.key[j + M] = 0;
		}
		for (int j = 0; j < M  ; j++) {	//分裂节点右子树重置
			spiltNode.childs[j + M] = null;			
		}
		newNode.count++;		//新节点数据大小+1
	}

	public void fillNodeIn(BTreeNode node, int key) {		//将数据插入到节点
		int i = node.count;
		if (node.isleaf) {
			while (i >= 1 && key < node.key[i - 1]) {
				node.key[i] = node.key[i - 1];
				i--;
			}
			node.key[i] = key;
			node.count++;
		} else {
			int j = 0;
			while (j < node.count && key > node.key[j]) {
				j++;
			}
			if (node.childs[j].count == M * 2 - 1) {
				split(node, j, node.childs[j]);
				if (key > node.key[j]) {
					j++;
				}
			}
			fillNodeIn(node.childs[j], key);
		}
	}

	public void print(BTreeNode node) {
		for (int i = 0; i < node.count; i++) {
			System.out.print(node.key[i] + " ");
		}
		if (!node.isleaf) {
			for (int j = 0; j <= node.count; j++) {
				if (node.childs[j] != null) {
					System.out.println();
					print(node.childs[j]);
				}
			}
		}
	}
	public void searchKey(MyBTree T, int x) {
		BTreeNode temp = new BTreeNode(M);
		temp = search(T.root, x);
		if (temp == null) {
			System.out.println("not exist in this tree");
		}
		else {
			print(temp);
		}
	}
	public static void main(String[] args) {
		int t = 3;
		MyBTree tree = new MyBTree(t);
		int arr[ ] = { 3,14,7,1,8,5,11,17,13,6,23,12,20,26,4,16,18,24,25,19,9,10,15,27,28,2} ;
		for (int i = 0; i < arr.length; i++) {
			tree.insert(tree, arr[i]);
			System.out.println(arr[i] + "插入后的Btree");
			tree.print(tree.root);
			System.out.println();
			System.out.println("----------");
		}
		tree.searchKey(tree, 2);
	}
}
