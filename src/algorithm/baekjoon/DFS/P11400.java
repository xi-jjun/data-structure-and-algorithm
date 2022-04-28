package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// https://johoonday.tistory.com/172 참고...
// baekJoon 11400 Platinum5 단절선
public class P11400 {
    static int V, E;
    static int count = 0;
    static int[] order;
    static Set<Node> visited = new HashSet<>();
    static List<List<Integer>> graph = new ArrayList<>();
    static List<Node> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        order = new int[V + 1];

        dfs(1, 0);

        answers.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        bw.write(answers.size() + "\n");
        for (Node answer : answers) {
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int dfs(int child, int parent) {
        order[child] = ++count; // 현재 child 순서에 1 추가

        int ret = order[child];

        for (Integer next : graph.get(child)) {
            // 다음에 탐색할 곳은 부모는 일단 아니어야 함. 왜? 바로 전에 부모에 의해 지금 child 를 탐색하는 중이기 때문
            if (next == parent) continue;

            if (order[next] == 0) { // 0 이라는 뜻은 아직 next node 를 방문 안했다는 뜻이다.
                // child 의 subtree 를 확인. 여기서 parent or child 로 안간다면,
                // low 의 값은 order[child] 보다 클 것이다.
                // 왜? → 우리는 항상 최소 ret 를 반환하기 때문. 따라서 low 의 값이 더 작다 == 'parent or child 를 만났다' 라는 것.
                int low = dfs(next, child);

                if (low > order[child]) {
                    if (child < next)
                        answers.add(new Node(child, next));
                    else
                        answers.add(new Node(next, child));
                }

                ret = Math.min(ret, low);
            } else {
                // next 까지 방문했었을 때 child 나 child 의 parent 를 만났었다면, ret 는 order[next] 로 설정됨.
                // 따라서 위 코드에서 low 와 비교할 때 low 의 값이 더 작아지게 되고, 그 뜻은 단절선이 아니게 된다는 것.
                ret = Math.min(ret, order[next]);
            }
        }

        return ret;
    }

    static class Node {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }
}
