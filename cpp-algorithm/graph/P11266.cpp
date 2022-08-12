#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

int V, E, cnt = 0;
vector<int> graph[10001];
int discovered_seq[10001];
bool cut_vertex[10001];

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> V >> E;
	int A, B;
	for (int i = 0; i < E; i++) {
		cin >> A >> B;
		graph[A].push_back(B);
		graph[B].push_back(A);
	}
	memset(discovered_seq, -1, sizeof(discovered_seq));
	memset(cut_vertex, false, sizeof(cut_vertex));
}

int find_cut_vertex(int now, bool is_root) {
	discovered_seq[now] = ++cnt;
	int ret = discovered_seq[now];
	int child = 0;

	for (int next : graph[now]) {
		if (discovered_seq[next] == -1) {
			child++;
			int subtree = find_cut_vertex(next, false);

			if (!is_root && subtree >= discovered_seq[now]) {
				cut_vertex[now] = true;
			}
			ret = min(ret, subtree);
		} else {
			ret = min(ret, discovered_seq[next]);
		}
	}

	if (is_root && child >= 2) cut_vertex[now] = true; // root는 child >=2 면 무조건 단절점
	return ret;
}

void solution() {
	for (int node = 1; node <= V; node++) {
		if (discovered_seq[node] == -1) {
			find_cut_vertex(node, true);
		}
	}

	vector<int> answer;
	for (int node = 1; node <= V; node++) {
		if (cut_vertex[node]) answer.push_back(node);
	}

	cout << answer.size() << "\n";
	for (int node : answer) cout << node << ' ';
}

int main() {
	input();
	solution();
	return 0;
}
