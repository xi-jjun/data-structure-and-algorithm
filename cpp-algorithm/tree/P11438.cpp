#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;

const int ROOT = 0;
int N, M;
vector<int> graph[100001];
int parent[100001][18];
int depth[100001];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
    int u, v;
	for (int i = 0; i < N - 1; i++) {
		cin >> u >> v;
        --u; --v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
}

void create_tree(int now) {
	for (auto next : graph[now]) {
		if (depth[next] == -1) {
			depth[next] = depth[now] + 1;
			parent[next][0] = now;
			create_tree(next);
		}
	}
}

void fill_parent() {
	for (int j = 0; j < 17; j++) {
		for (int i = 1; i < N; i++) {
			if (parent[i][j] != -1) {
				parent[i][j + 1] = parent[parent[i][j]][j];
			}
		}
	}
}

int increase_u(int u, int diff) {
	for (int i = 0; diff; i++) {
		if (diff % 2) u = parent[u][i];
		diff /= 2;
	}
	return u;
}

int LCA(int u, int v) {
	if (depth[u] < depth[v]) swap(u, v);
	int diff = depth[u] - depth[v];

	u = increase_u(u, diff);

	if (u != v) {
		for (int i = 17; i >= 0; i--) {
			if (parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		u = parent[u][0];
	}

	return u;
}

void solution() {
	memset(parent, -1, sizeof(parent));
	fill(depth, depth + N, -1);
	depth[0] = 0;

	create_tree(ROOT);

	fill_parent();

	cin >> M;
	int u, v;
	for (int i = 0; i < M; i++) {
		cin >> u >> v;
        --u; --v;
		int answer = LCA(u, v) + 1;
		cout << answer << "\n";
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
