package datastructure.binarysearchtree;

public class BstTest {
	public static void main(String[] args) {
		Node root = new Node(50);
		BinarySearchTree bst = new BinarySearchTree(root);
		bst.insert(30);
		bst.insert(24);
		bst.insert(5);
		bst.insert(28);
		bst.insert(45);
		bst.insert(98);
		bst.insert(52);
		bst.insert(60);

		bst.postOrderTraversal(root);
	}
}
