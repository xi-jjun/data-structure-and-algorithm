#include <iostream>

using namespace std;

int n, m;
int parent[1000001];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> m;

	for (int node = 0; node <= n; node++) parent[node] = node; // init parent 
}

int find(int node) {
	if (node == parent[node]) return node;

	int node_parent = parent[node];
	parent[node] = find(node_parent);
	return parent[node];
}

void merge(int a, int b) {
	int root_a = find(a);
	int root_b = find(b);
	if (root_a == root_b) return;
	parent[root_a] = root_b;
}

void print_ans(int a, int b) {
	if (find(a) == find(b)) cout << "YES\n";
	else cout << "NO\n";
}

void solution() {
	// 1. mode = 0 => merge
	// 2. mode = 1 => 부모확인하기
	int mode, a, b;
	while (m--) {
		cin >> mode >> a >> b;
		if (mode == 0) {
			merge(a, b);
		} else {
			print_ans(a, b);
		}
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
