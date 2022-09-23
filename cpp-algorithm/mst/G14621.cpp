#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Edge {
    int a;
    int b;
    int cost;
};

struct cmp {
    bool operator()(Edge &e1, Edge &e2) {
        return e1.cost > e2.cost;
    }
};

int N, M;
char sex[1001];
int parents[1001];
priority_queue<Edge, vector<Edge>, cmp> pq;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M;
    for (int node = 1; node <= N; node++) cin >> sex[node];

    int u, v, d;
    for (int i = 0; i < M; i++) {
        cin >> u >> v >> d;
        if (sex[u] == sex[v]) continue;
        pq.push({u, v, d});
    }
}

int find(int node) {
    if (node == parents[node]) return node;
    parents[node] = find(parents[node]);
    return parents[node];
}

bool merge(int a, int b) {
    int root_a = find(a);
    int root_b = find(b);
    if (root_a == root_b) return false;
    parents[root_a] = root_b;
    return true;
}

void solution() {
    int count = 0;
    for (int node = 1; node <= N; node++) parents[node] = node;

    int answer = 0;
    while (!pq.empty()) {
        Edge edge = pq.top();
        if (merge(edge.a, edge.b)) {
            count++;
            answer += edge.cost;
        }
        pq.pop();
    }

    if (count == N - 1) cout << answer;
    else cout << "-1";
}

int main() {
    input();
    solution();
    return 0;
}