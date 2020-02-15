package Learning.arithmetic.tree;

class Node{
	
	private char data;
	private Node leftNode;
	private Node rightNode;
	
	public Node(char data, Node leftNode, Node rightNode) {
		super();
		this.setData(data);
		this.setLeftNode(leftNode);
		this.setRightNode(rightNode);
	}

	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	
}

public class BinaryTree {
	
	public static void main(String[] args) {
		Node D = new Node('D', null, null);
		Node H = new Node('H', null, null);
		Node K = new Node('K', null, null);
		Node C = new Node('C', D, null);
		Node B = new Node('B', null, C);
		Node G = new Node('G', H, K);
		Node F = new Node('F', G, null);
		Node E = new Node('E', null, F);
		Node A = new Node('A', B, E);		//就是root结点
		
		BinaryTree binaryTree = new BinaryTree();
		System.out.println("前");
		binaryTree.pre(A);
		System.out.println("中");
		binaryTree.in(A);
		System.out.println("后");
		binaryTree.post(A);
		
	}
	
	public void print(Node node) {
		System.out.print(node.getData());
	}
	
	public void pre(Node root) {		//前序遍历 根节点 (输出) 左子树  右子树
		print(root);
		if(root.getLeftNode() != null) {
			pre(root.getLeftNode());
		}
		if(root.getRightNode() != null) {
			pre(root.getRightNode());
		}
	}
	
	public void in(Node root) {		//中序遍历  左子树 根节点 (输出)  右子树
		if(root.getLeftNode() != null) {
			pre(root.getLeftNode());
		}
		print(root);
		if(root.getRightNode() != null) {
			pre(root.getRightNode());
		}
	}
	
	public void post(Node root) {		//中序遍历  左子树   右子树 根节点 (输出)
		if(root.getLeftNode() != null) {
			pre(root.getLeftNode());
		}
		if(root.getRightNode() != null) {
			pre(root.getRightNode());
		}
		print(root);
	}
}
//ABCDEFGHK
//ABCDEFGHK

