#include <iostream>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

const int INF = 1061109567;
int V, E, K;
vector<pair<int, int>> graph[20001];
int dp[20001];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> V >> E >> K;

	int u, v, w;
	for (int i = 0; i < E; i++) {
		cin >> u >> v >> w;
		graph[u].push_back({-w, v});
	}
}

void solution() {
	bool visited[V + 1];
	memset(visited, false, sizeof(visited));
	memset(dp, 0x3f, sizeof(int) * (V + 1));
	priority_queue<pair<int, int>> pq;
	dp[K] = 0;
	pq.push({0, K});

	while (!pq.empty()) {
		int now = pq.top().second;
		int cost = -pq.top().first;
		pq.pop();

		if (visited[now]) continue;

		visited[now] = true;
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now][i].second;
			int next_cost = -graph[now][i].first;

			if (dp[next] > next_cost + dp[now]) {
				dp[next] = next_cost + dp[now];
				pq.push({-dp[next], next});
			}
		}
	}

	for (int i = 1; i <= V; i++) {
		if (dp[i] == INF) cout << "INF\n";
		else cout << dp[i] << "\n";
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
