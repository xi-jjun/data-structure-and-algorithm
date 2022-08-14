// https://everenew.tistory.com/96
#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

#define MAX_ 100001
#define INF 1000001
#define TREE_H 20

using namespace std;

int N, K, D, E, min_result, max_result;
vector<pair<int, int>> graph[MAX_];
int depth[MAX_];
int parents[MAX_][TREE_H];
int min_road[MAX_][TREE_H], max_road[MAX_][TREE_H];

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	int A, B, C;
	for (int i = 0; i < N - 1; i++) {
		cin >> A >> B >> C;
		graph[A].push_back({B, C});
		graph[B].push_back({A, C});
	}
}

void reset() {
	memset(parents, 0, sizeof(parents));
	memset(min_road, INF, sizeof(min_road));
	memset(max_road, 0, sizeof(max_road));
}

void find_parents(int parent, int now, int now_depth, int road_dist) {
	depth[now] = now_depth;
	parents[now][0] = parent;
	min_road[now][0] = max_road[now][0] = road_dist;
	
	for (pair<int, int> next : graph[now]) {
		if (next.first != parent) {
			find_parents(now, next.first, now_depth + 1, next.second);
		}
	}
}

void make_parents() {
	for (int k = 0; k < TREE_H; k++) {
		for (int city = 2; city <= N; city++) {
			if (parents[city][k] != 0) {
				int this_city_parent = parents[city][k];
				parents[city][k + 1] = parents[this_city_parent][k];
				min_road[city][k + 1] = min(min_road[city][k], min_road[this_city_parent][k]);
				max_road[city][k + 1] = max(max_road[city][k], max_road[this_city_parent][k]);
			}
		}
	}
}

int increase_u(int u, int diff) {
	for (int i = 0; diff; i++) {
		if (diff % 2) {
			min_result = min(min_road[u][i], min_result);
			max_result = max(max_road[u][i], max_result);
			u = parents[u][i];
		}
		diff /= 2;
	}

	return u;
}

void increase_u_v(int& u, int& v) {
	for (int k = TREE_H - 1; k >= 0; k--) {
		if (parents[u][k] != 0 && parents[u][k] != parents[v][k]) {
			min_result = min(min_result, min_road[u][k]);
			min_result = min(min_result, min_road[v][k]);
			max_result = max(max_result, max_road[u][k]);
			max_result = max(max_result, max_road[v][k]);

			u = parents[u][k];
			v = parents[v][k];
		}
	}
}

pair<int, int> get_min_max_distance(int u, int v) {
	min_result = INF; 
	max_result = 0;
	
	if (depth[u] != depth[v]) {
		if (depth[u] < depth[v]) swap(u, v);
		int diff = depth[u] - depth[v];

		u = increase_u(u, diff);
	}

	if (u != v) {
		increase_u_v(u, v);
		min_result = min(min_result, min_road[u][0]);
		min_result = min(min_result, min_road[v][0]);
		
		max_result = max(max_result, max_road[u][0]);
		max_result = max(max_result, max_road[v][0]);
	}
	
	return make_pair(min_result, max_result);
}

void solution() {
	reset();

	find_parents(0, 1, 0, 0);
	make_parents();

	pair<int, int> answer;
	cin >> K;
	for (int i = 0; i < K; i++) {
		cin >> D >> E;
		answer = get_min_max_distance(D, E);
		cout << answer.first << ' ' << answer.second << "\n";
	}
}

int main () {
	input();
	solution();
	return 0;
}
