package BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 1991 silver1 트리 순회
public class S1991 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static class Node {
		Node leftChild;
		Node rightChild;
		String key;

		public Node(String key) {
			this.key = key;
		}
	}

	static class BinarySearchTree {
		Node root;

		public BinarySearchTree(Node root) {
			this.root = root;
		}

		void preOrderTraversal(Node focusNode) throws IOException {
			if (focusNode == null) {
				return;
			}
			bw.write(focusNode.key);
			preOrderTraversal(focusNode.leftChild);
			preOrderTraversal(focusNode.rightChild);
		}

		void inOrderTraversal(Node focusNode) throws IOException {
			if (focusNode == null) {
				return;
			}
			inOrderTraversal(focusNode.leftChild);
			bw.write(focusNode.key);
			inOrderTraversal(focusNode.rightChild);
		}

		void postOrderTraversal(Node focusNode) throws IOException {
			if (focusNode == null) {
				return;
			}
			postOrderTraversal(focusNode.leftChild);
			postOrderTraversal(focusNode.rightChild);
			bw.write(focusNode.key);
		}
	}

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N];

		for (int i = 0; i < N; i++) {
			nodes[i] = new Node((char) (i + 65) + "");
		}

		Node root = nodes[0];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String parentKey = st.nextToken();
			String leftChildKey = st.nextToken();
			String rightChildKey = st.nextToken();

			Node parent = nodes[parentKey.charAt(0) - 65];

			if (!leftChildKey.equals(".")) {
				parent.leftChild = nodes[leftChildKey.charAt(0) - 65];
			}
			if (!rightChildKey.equals(".")) {
				parent.rightChild = nodes[rightChildKey.charAt(0) - 65];
			}
		}

		BinarySearchTree bst = new BinarySearchTree(nodes[0]);

		bst.preOrderTraversal(root);
		bw.write("\n");
		bst.inOrderTraversal(root);
		bw.write("\n");
		bst.postOrderTraversal(root);
		bw.write("\n");

		bw.flush();
		bw.close();
	}
}
