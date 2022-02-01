package datastructure.binarysearchtree;

public class BinarySearchTree {
	private Node root;

	public BinarySearchTree() {
	}

	public BinarySearchTree(Node root) {
		this.root = root;
	}

	public Node findNode(Integer data) {
		if (root == null) {
			return null;
		}

		Node focusNode = root;
		while (focusNode.data != data) {
			if (data < focusNode.data) {
				focusNode = focusNode.leftChild;
			} else {
				focusNode = focusNode.rightChild;
			}

			if (focusNode == null) {
				return null;
			}
		}

		return focusNode;
	}

	/**
	 * tree 에 Node 삽입
	 *
	 * @param data : Node value
	 *             if data is existed, then stop inserting.
	 */
	public void insert(int data) {
		if (findNode(data) != null) {
			return;
		}

		Node newNode = new Node(data);

		if (root == null) {
			root = newNode;
		} else {
			Node searchNode = root;
			Node searchNodeParent;

			while (true) {
				searchNodeParent = searchNode;

				if (data < searchNodeParent.data) {
					searchNode = searchNodeParent.leftChild;

					if (searchNode == null) {
						searchNodeParent.leftChild = newNode;
						return;
					}
				} else {
					searchNode = searchNodeParent.rightChild;

					if (searchNode == null) {
						searchNodeParent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public void postOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			postOrderTraversal(focusNode.leftChild);
			postOrderTraversal(focusNode.rightChild);
			System.out.print(focusNode.data + " ");
		}
	}
}
