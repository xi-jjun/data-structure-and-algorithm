#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

struct Edge {
    int a;
    int b;
    int cost;

    Edge(int _a, int _b, int _c) : a(_a), b(_b), cost(_c) {};
};

bool comp(Edge &e1, Edge &e2) {
    return e1.cost < e2.cost;
}

int V, E;
vector<int> parents;
vector<Edge> edges;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int A, B, C;
    cin >> V >> E;
    for (int i = 0; i < E; i++) {
        cin >> A >> B >> C;
        edges.push_back(Edge(A, B, C));
    }
}

void make_init_parents() {
    parents.resize(V + 1, 0);
    for (int node = 1; node <= V; node++) parents[node] = node;
}

int find(int node) {
    if (node == parents[node]) return node;
    parents[node] = find(parents[node]);
    return parents[node];
}

void merge(int a, int b) {
    int a_root = find(a);
    int b_root = find(b);
    if (a_root != b_root) parents[a_root] = b_root;
}

void solution() {
    make_init_parents();

    int answer = 0;
    sort(edges.begin(), edges.end(), comp);
    for (Edge edge : edges) {
        int a = edge.a;
        int b = edge.b;
        int cost = edge.cost;

        if (find(a) != find(b)) {
            merge(a, b);
            answer += cost;
        }
    }
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}