#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int parent[1001];
vector<pair<int, pair<int, int>>> edges;

void init();
int find(int node);

void get_input() {
	init();
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int a, b, cost;
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		cin >> a >> b >> cost;
		edges.push_back({cost, {a, b}});
	}
}

void init() {
	sort(edges.begin(), edges.end());
	for (int i = 1; i <= N; i++) parent[i] = i;
}

void merge(int a, int b) {
	int root_a = find(a);
	int root_b = find(b);
	if (root_a == root_b) return;

	parent[root_a] = root_b;
}

int find(int node) {
	if (parent[node] == node) return node;
	int this_parent = find(parent[node]);
	parent[node] = this_parent;
	return this_parent;
}

void solution() {
	init();

	int answer = 0;
	for (auto edge : edges) {
		int cost = edge.first;
		int a = edge.second.first;
		int b = edge.second.second;

		if (find(a) != find(b)) {
			answer += cost;
			merge(a, b);
		}
	}
	cout << answer;
}


int main() {
	get_input();
	solution();
	return 0;
}
