#include <iostream>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>

using namespace std;

int N;
int dp[501];
vector<int> graph[501];
int in_degree[501];
int build_time[501];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	int building_num;
	for (int i = 1; i <= N; i++) {
		cin >> build_time[i] >> building_num;
		while (building_num != -1) {
			graph[building_num].push_back(i); // i를 짓기 위해서 building_num건물이 필요
			in_degree[i]++;
			cin >> building_num;
		}
	}
}

void solution() {
	queue<int> q;
	for (int i = 1; i <= N; i++) {
		if (!in_degree[i]) q.push(i);
	}
	for (int i = 1; i <= N; i++) {
		dp[i] = build_time[i];
	}

	while (!q.empty()) {
		int now = q.front();
		q.pop();

		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now][i];
			in_degree[next]--;

			if (in_degree[next] == 0) {
				q.push(next);
			}
			dp[next] = max(dp[next], build_time[next] + dp[now]);
		}
	}

	for (int i = 1; i <= N; i++) {
		cout << dp[i] << "\n";
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
