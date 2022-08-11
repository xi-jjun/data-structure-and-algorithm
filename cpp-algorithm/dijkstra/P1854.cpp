// https://justicehui.github.io/ps/2019/04/10/BOJ1854/
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m, k, from, to, cost;
vector<pair<int, int>> graph[1001];
priority_queue<int> heap[1001];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> m >> k;
	for (int i = 0; i < m; i++) {
		cin >> from >> to >> cost;
		graph[from].push_back({to, cost});
	}
}

void kth_dijkstra() {
	priority_queue<pair<int, int>> pq;
	pq.push({0, 1});
	heap[1].push(0); // 1->1 의 1번째 최단경로는 길이가 0이다.

	while (!pq.empty()) {
		int now = pq.top().second;
		int now_cost = -pq.top().first;
		pq.pop();

		for (pair<int, int> next_node : graph[now]) {
			int next = next_node.first;
			int next_cost = next_node.second + now_cost;

			if (heap[next].size() < k) {
				heap[next].push(next_cost);
				pq.push({-next_cost, next});
			} else if (heap[next].top() > next_cost) {
				heap[next].pop();
				heap[next].push(next_cost);
				pq.push({-next_cost, next});
			}
		}
	}
}

void solution() {
	kth_dijkstra();

	for (int city = 1; city <= n; city++) {
		if (heap[city].size() != k) cout << "-1\n";
		else cout << heap[city].top() << "\n";
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
