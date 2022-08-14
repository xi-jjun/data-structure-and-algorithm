#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>

#define MAX_ 501
#define INF 10000000000
#define START_CITY 1

typedef long long ll;
using namespace std;

int N, M, A, B, C;
ll dp[MAX_];
vector<pair<int, pair<int, int>>> edges;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		cin >> A >> B >> C;
		edges.push_back({A, {B, C}});
	}
}

bool bellman_ford(int start) {
	dp[start] = 0;
	
	for (int stage = 1; stage <= N; stage++) {
		for (pair<int, pair<int, int>> edge : edges) {
			int from = edge.first;
			int to = edge.second.first;
			int cost = edge.second.second;
	
			if (dp[from] == INF) continue;

			if (dp[to] > dp[from] + cost) {
				dp[to] = dp[from] + cost;
				if (stage == N) return true;
			}
		} 
	}

	return false;
}

void solution() {
	fill(dp, dp + N + 1, INF);
	bool has_neg_cycles = bellman_ford(START_CITY);

	if (has_neg_cycles) cout << "-1";
	else {
		for (int to = 2; to <= N; to++) {
			if (dp[to] == INF) cout << "-1\n";
			else cout << dp[to] << "\n";
		}
	}
}

int main() {
	input();
	solution();
	return 0;
}
