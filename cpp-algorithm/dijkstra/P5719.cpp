// https://2jinishappy.tistory.com/303
#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

const int INF = 987654321;
int N, M, start_city, end_city;
vector<vector<int>> prev_nodes;
bool erased[500][500];
vector<vector<pair<int, int>>> graph;

void init() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
}

void reset() {
	graph.clear();
	prev_nodes.clear();
	memset(erased, false, sizeof(erased));
	graph.clear();
	graph = vector<vector<pair<int, int>>>(N);	
}

bool get_input() {
	cin >> N >> M;
	reset();
	if (N == 0 && M == 0) return false;
	cin >> start_city >> end_city;

	int U, V, P;
	for (int i = 0; i < M; i++) {
		cin >> U >> V >> P;
		graph[U].push_back({V, P});
	}

	return true;
}

int dijkstra() {
	prev_nodes = vector<vector<int>>(N + 1);
	vector<int> dp(N + 1, INF);
	priority_queue<pair<int, int>> pq;
	dp[start_city] = 0;
	pq.push({0, start_city});

	while (!pq.empty()) {
		int now = pq.top().second;
		int now_cost = -pq.top().first;
		pq.pop();

		if (now_cost > dp[now]) continue; 

		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now][i].first;
			int next_cost = graph[now][i].second;

			if (erased[now][next]) continue;

			if (dp[next] == dp[now] + next_cost) {
				prev_nodes[next].push_back(now); // next의 이전은 now 이다.
			} else if (dp[next] > dp[now] + next_cost) {
				prev_nodes[next].clear(); // 새로운 최단거리의 등장으로 이전의 내용은 초기화
				prev_nodes[next].push_back(now);
				dp[next] = dp[now] + next_cost;
				pq.push({-dp[next], next});
			}
		}
	}

	return dp[end_city] == INF ? -1 : dp[end_city];
}

void check_remove_edge(int now) {
	for (int i = 0; i < prev_nodes[now].size(); i++) {
		int ex = prev_nodes[now][i];
		for (int j = 0; j < graph[ex].size(); j++) {
			// now 가 실제 경로이고, 아직 제거되지 않았다면
			if (graph[ex][j].first == now && !erased[ex][now]) {
				erased[ex][now] = true;
				check_remove_edge(ex);
				break;
			}
		}
	}
}

int main() {
	init();

	int answer;
	while (get_input()) {
		answer = dijkstra();
		check_remove_edge(end_city);
		answer = dijkstra();
		cout << answer << "\n";
	}

	return 0;
}
