package algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class G14725 {
    static int N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Node root = new Node("root");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            List<String> foods = new ArrayList<>();
            while (st.hasMoreTokens()) {
                foods.add(st.nextToken());
            }

            Node now = null;
            for (String food : foods) {
                now = findOrCreate(food, (now == null ? root : now));
            }
        }

        for (Node child : root.children) {
            bw.write(child.value + "\n");
            preOrder(child, 1);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static Node findOrCreate(String value, Node searchStartNode) {
        if (searchStartNode.isChild(value)) {
            return searchStartNode.getChild(value);
        }

        Node newNode = new Node(value);
        searchStartNode.children.add(newNode);

        return newNode;
    }

    private static void preOrder(Node now, int depth) throws IOException {
        if (now.children.size() == 0) return;

        StringBuilder indent = new StringBuilder();
        indent.append("--".repeat(Math.max(0, depth)));

        for (Node child : now.children) {
            bw.write(indent + child.value + "\n");
            preOrder(child, depth + 1);
        }
    }

    static class Node implements Comparable<Node> {
        String value;
        Set<Node> children = new TreeSet<>();

        public Node(String value) {
            this.value = value;
        }

        public boolean isSame(String value) {
            return this.value.equals(value);
        }

        public Node getChild(String value) {
            return children.stream()
                    .filter(c -> c.isSame(value))
                    .findFirst().orElse(null);
        }

        public boolean isChild(String value) {
            return this.children.stream()
                    .anyMatch(c -> c.isSame(value));
        }

        @Override
        public boolean equals(Object obj) {
            Node other = (Node) obj;
            return this.value.equals(other.value);
        }

        @Override
        public int compareTo(Node o) {
            return this.value.compareTo(o.value);
        }
    }
}
