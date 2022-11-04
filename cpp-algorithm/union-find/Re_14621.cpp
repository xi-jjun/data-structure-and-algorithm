#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <set>
#include <cstring>

using namespace std;

typedef tuple<int, int, int> tiii;

int N, M;
char gender[10001];
int parents[10001];
vector<tiii> edges;

bool cmp(tiii t1, tiii t2) {
    return get<2>(t1) < get<2>(t2);
}

void input() {
    ios::sync_with_stdio(false);
    int u, v, d;
    cin >> N >> M;
    for (int i = 1; i <= N; i++) {
        cin >> gender[i];
    }

    for (int i = 0; i < M; i++) {
        cin >> u >> v >> d;
        edges.push_back({u, v, d});
    }
}

void init() {
    for (int i = 1; i <= N; i++) {
        parents[i] = i;
    }
}

int find(int node) {
    if (parents[node] == node) {
        return node;
    }

    parents[node] = find(parents[node]);
    return parents[node];
}

void merge(int a, int b) {
    if (a == b) return;

    int a_root = find(a);
    int b_root = find(b);

    if (a_root != b_root) {
        parents[a_root] = b_root;
    }
}

void solution() {
    init();
    set<int> connected;
    sort(edges.begin(), edges.end(), cmp);

    int answer = 0;
    int univ1, univ2, distance;
    for (auto edge : edges) {
        tie(univ1, univ2, distance) = edge;

        if (gender[univ1] != gender[univ2]) {
            if (find(univ1) != find(univ2)) {
                merge(univ1, univ2);
                connected.insert(univ1);
                connected.insert(univ2);
                answer += distance;
            }
        }
    }

    cout << (connected.size() != N ? -1 : answer);
}

int main() {
    input();
    solution();
    return 0;
}