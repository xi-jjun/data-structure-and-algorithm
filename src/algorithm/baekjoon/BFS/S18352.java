package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S18352 {
    static int N, M, K, X;
    static List<Integer> answer = new ArrayList<>();
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
        }

        bfs(X);

        if (answer.size() == 0) {
            bw.write("-1");
        } else {
            Collections.sort(answer);
            for (Integer ans : answer) {
                bw.write(ans + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        if (K == 0) {
            answer.add(start);
        }

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int currNodeNumber = currNode.number;
            int currDistance = currNode.distance;

            for (int i = 0; i < graph.get(currNodeNumber).size(); i++) {
                Integer nextNodeNumber = graph.get(currNodeNumber).get(i);

                if (!visited[nextNodeNumber]) {
                    visited[nextNodeNumber] = true;
                    if (currDistance + 1 == K) {
                        answer.add(nextNodeNumber);
                    }
                    pq.offer(new Node(nextNodeNumber, currDistance + 1));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int number;
        int distance;

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
