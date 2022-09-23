#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

struct Edge {
    double distance;
    int a;
    int b;
};

bool cmp(Edge e1, Edge e2) {
    return e1.distance < e2.distance;
}

int n;
int parents[101];
vector<Edge> edges;
vector<pair<int, int>> coords(101, {-1, -1});

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n;

    double x, y;
    for (int node = 1; node <= n; node++) {
        cin >> x >> y;
        coords[node] = {x, y};
    }
}

double get_distance(int from, int to) {
    pair<int, int> a = coords[from];
    pair<int, int> b = coords[to];
    return sqrt(pow(a.first - b.first, 2) + pow(a.second - b.second, 2));
}

void make_edges() {
    for (int std_node = 1; std_node < n; std_node++) {
        for (int cmp_node = std_node + 1; cmp_node <= n; cmp_node++) {
            double distance = get_distance(std_node, cmp_node);
            edges.push_back({distance, std_node, cmp_node});
        }
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
    for (int node = 1; node <= n; node++) parents[node] = node;

    make_edges();
    sort(edges.begin(), edges.end(), cmp);

    double answer = 0;
    for (Edge edge : edges) {
        int a = edge.a;
        int b = edge.b;
        double dist = edge.distance;

        if (merge(a, b)) answer += dist;
    }   

    cout.precision(2);
    cout << fixed;
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}