#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge {
    int from;
    int to;
    int cost;

    Edge(int _a, int _b, int _c) : from(_a), to(_b), cost(_c) {};
};

bool comp(Edge &e1, Edge &e2) {
    return e1.cost < e2.cost;
}

int N, M;
vector<int> parents;
vector<Edge> edges;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int a, b, c;
    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        cin >> a >> b >> c;
        edges.push_back(Edge(a, b, c));
    }
}

void make_init_parents() {
    parents.resize(N + 1, 0);
    for (int i = 1; i <= N; i++) parents[i] = i;
}

int find(int node) {
    if (node == parents[node]) return node;
    parents[node] = find(parents[node]);
    return parents[node];
}

void merge(int v, int u) {
    int v_root = find(v);
    int u_root = find(u);

    if (v_root != u_root) {
        parents[v_root] = u_root;
    }
}

void solution() {
    make_init_parents();

    sort(edges.begin(), edges.end(), comp);

    int answer = 0;
    for (Edge edge : edges) {
        int from = edge.from;
        int to = edge.to;
        int cost = edge.cost;

        if (find(from) != find(to)) {
            answer += cost;
            merge(from, to);
        }
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}