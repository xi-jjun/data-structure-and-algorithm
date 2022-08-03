#include <iostream>
#include <cstring>
#include <vector>
#include <map>
#include <queue>

using namespace std;

int n, m;
map<int, vector<int>> graph;

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> m;
}

bool bfs(int start, int target) {
	if (start == target) return true;
	queue<int> q;
	bool visited[n + 1];
	memset(visited, false, sizeof(visited));
	q.push(start);
	visited[start] = true;

	while (!q.empty()) {
		int now = q.front();
		q.pop();
		if (now == target) return true;
		cout << "now : " << now << "\n";

		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now][i];
			cout << "next : " << next << "\n";
			if (!visited[next]) {
				cout << "visited checked : " << next << "\n";
				if (next == target) return true;
				visited[next] = true;
				q.push(next);
			}
		}
	}

	return false;
}

void print_answer(int a, int b) {
	if (bfs(a, b)) cout << "YES\n";
	else cout << "NO\n";
}

void solution() {
	int mode, a, b;
	for (int i = 0; i < m; i++) {
		cin >> mode >> a >> b;
		if (mode == 0) {
			graph[a].push_back(b);
			graph[b].push_back(a);
		} else {
			print_answer(a, b);
		}
	}
	
	for (int i = 0; i <= n; i++) {
		cout << "\nthis " << i << " : "; 
		for (auto e : graph[i]) cout << e << ' ';
	}
}

int main() {
	get_input();

	solution();

	return 0;
}
