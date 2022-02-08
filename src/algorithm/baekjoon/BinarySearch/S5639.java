package BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 5639 silver1 이진 검색 트리
public class S5639 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int rootData = Integer.parseInt(br.readLine());
		Node root = new Node(rootData);
		BinarySearchTree bst = new BinarySearchTree(root, bw);

		String input = "";
		while ((input = br.readLine()) != null) {
			bst.insert(Integer.parseInt(input));
		}

		bst.postOrderTraversal(root);

		bw.flush();
		bw.close();
	}

	static class Node {
		Node leftChild;
		Node rightChild;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	static class BinarySearchTree {
		final BufferedWriter bw;
		final Node root;

		public BinarySearchTree(Node root, BufferedWriter bw) {
			this.root = root;
			this.bw = bw;
		}

		public void insert(int data) {
			Node newNode = new Node(data);

			Node focusNode = root;
			Node parentNode;

			while (true) {
				parentNode = focusNode;

				if (parentNode.data > data) {
					focusNode = parentNode.leftChild;

					if (focusNode == null) {
						parentNode.leftChild = newNode;
						return;
					}
				} else {
					focusNode = parentNode.rightChild;

					if (focusNode == null) {
						parentNode.rightChild = newNode;
						return;
					}
				}
			}
		}

		public void postOrderTraversal(Node focusNode) throws IOException {
			if (focusNode != null) {
				postOrderTraversal(focusNode.leftChild);
				postOrderTraversal(focusNode.rightChild);
				bw.write(focusNode.data + "\n");
			}
		}
	}
}
