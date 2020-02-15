package Learning.arithmetic;



public class BinaryTree {
	int data;
	BinaryTree left;
	BinaryTree right;
	public BinaryTree(int data){
		this.data=data;
		this.left=null;
		this.right=null;
	}
	//二叉树排序，中序遍历，二叉树建立完成后就已经完成排序
	public void insert(BinaryTree root ,int data){
		if (root.data<data){
			if(root.right==null){
				root.right=new BinaryTree(data);
			}else {
				insert(root.right,data);
			}
		}else {
			if (root.left==null){
				root.left=new BinaryTree(data);
			}else {
				insert(root.left,data);
			}
		}
	}

	//中序遍历 递归的魅力
	public void in (BinaryTree root){
		if(root!=null){
			in(root.left);
			System.out.println(root.data);
			in(root.right);
		}
	}

	//二分查找
	public void find( BinaryTree root, int searchdata){
			if(root.data==searchdata){
				System.out.println("已经找到");
			}else{
				if(root.data<searchdata){
					if(root.right==null){
						System.out.println("不存在该数字");
					}else{
						find(root.right,searchdata);
					}
				}else {
					if(root.left==null){
						System.out.println("不存在该数字");
					}else {
						find(root.left,searchdata);
					}
				}
			}
	}
	public static void main(String[] args) {
		int data[]={0,4,3,8,5,2,7,9,10};
		BinaryTree root=new BinaryTree(data[0]);
		for (int i = 1; i <data.length ; i++) {

			root.insert(root,data[i]);
		}
		root.in(root);
		root.find(root, 1);
	}

}

