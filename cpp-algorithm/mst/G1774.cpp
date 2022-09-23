// https://m.blog.naver.com/PostView.nhn?blogId=gydbslove&logNo=221595838218&proxyReferer=https:%2F%2Fwww.google.com%2F
// 처음에 간선을 만들어가는 과정을 배울만 했다.
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

struct Edge {
    int a;
    int b;
    double distance;

    Edge(int _x, int _y, double d) : distance(d), a(_x), b(_y) {};
};

bool comp(Edge &e1, Edge &e2) {
    return e1.distance < e2.distance;
}

int N, M;
int parents[1001];
vector<Edge> edges;
vector<pair<int, int>> coords(1001, {0, 0});

int find(int node) {
    if (node == parents[node]) return node;
    parents[node] = find(parents[node]);
    return parents[node];
}

bool merge(int a, int b) {
    int root_a = find(a);
    int root_b = find(b);
    if (root_a != root_b) {
        parents[root_a] = root_b;
        return true;
    }

    return false;
}

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cout.precision(2);
    cout << fixed;
    cin >> N >> M;
    int X, Y, A, B;
    for (int node = 1; node <= N; node++) {
        cin >> X >> Y;
        coords[node] = {X, Y};
    }

    for (int node = 1; node <= N; node++) parents[node] = node;
    for (int i = 0; i < M; i++) {
        cin >> A >> B;
        merge(A, B);
    }
}

double get_dist(int from, int to) {
    pair<int, int> a = coords[from];
    pair<int, int> b = coords[to];
    return sqrt(pow(a.first - b.first, 2) + pow(a.second - b.second, 2));
}

void make_edges() {
    for (int std_node = 1; std_node < N; std_node++) { // std와 to 1번 비교했으면 ,
        for (int to_node = std_node + 1; to_node <= N; to_node++) { // to와 std를 또 비교할 필요는 없기에 std_node + 1번째부터 탐색하는 것
            double distance = get_dist(std_node, to_node);
            edges.push_back(Edge(std_node, to_node, distance));
        }
    }
}

void solution() {
    make_edges();
    sort(edges.begin(), edges.end(), comp);

    double answer = 0;
    for (Edge edge : edges) {
        int a = edge.a;
        int b = edge.b;
        double cost = edge.distance;

        if (merge(a, b)) {
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