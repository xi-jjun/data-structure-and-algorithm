// https://cocoon1787.tistory.com/479
#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>

#define SEA 0

using namespace std;

const int dx[4] = {-1, 0, 1, 0};
const int dy[4] = {0, 1, 0, -1};
int N, M, total_islands = 0, answer = 0;
int parent[7];
int map[11][11];
bool visited[11][11];
vector<int> graph[11];
vector<pair<int, pair<int, int>>> edges;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
		}
	}

	for (int island = 0; island < 7; island++) {
		parent[island] = island;
	}
}

bool in_range(int x, int y) {
	return !(x < 0 || x >= N || y < 0 || y >= M);
}

void give_number(int x, int y, int seq) {
	if (map[x][y] == 0) return;
	visited[x][y] = true;
	map[x][y] = seq;

	for (int d = 0; d < 4; d++) {
		int next_x = x + dx[d];
		int next_y = y + dy[d];

		if (in_range(next_x, next_y) && !visited[next_x][next_y]) {
			give_number(next_x, next_y, seq);
		}
	}
}

void give_island_seq_number() {
	int seq = 1;
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < M; y++) {
			if (map[x][y] != SEA && !visited[x][y]) {
				give_number(x, y, seq);
				total_islands++;
				++seq;
			}
		}
	}
}

void make_edges(int start_x, int start_y) {
	int island_num = map[start_x][start_y];
	for (int d = 0; d < 4; d++) {
		int now_x = start_x;
		int now_y = start_y;
		int distance = 0;

		while (true) {
			now_x += dx[d];
			now_y += dy[d];

			if (!in_range(now_x, now_y) || map[now_x][now_y] == island_num) break;

			if (map[now_x][now_y] != SEA) {
				edges.push_back({distance, {island_num, map[now_x][now_y]}});
				break;
			}
			distance++;
		}
	}
}

void get_distance_between_islands() {
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < M; y++) {
			if (map[x][y] != SEA) {
				make_edges(x, y);
			}
		}
	}
}

int find(int island) {
	if (parent[island] == island) return island;

	parent[island] = find(parent[island]);
	return parent[island];
}

bool merge(int u, int v) {
	int root_u = find(u);
	int root_v = find(v);

	if (root_u != root_v) {
		parent[root_u] = root_v;

		graph[u].push_back(v);
		graph[v].push_back(u);
		return true;
	}
	return false;
}

void mst() {
	for (pair<int, pair<int, int>> edge : edges) {
		int from = edge.second.first;
		int to = edge.second.second;
		int distance = edge.first;

    if (distance < 2) continue;

		if (merge(from, to)) {
			answer += distance;
		}
	}
}

bool is_all_connected() {
	bool check[11];
	memset(check, false, sizeof(check));
	int count = 0;
	queue<int> q;
	q.push(1);
	check[1] = true;

	while (!q.empty()) {
		int now = q.front();
		q.pop();

		count++;

		for (int next : graph[now]) {
			if (!check[next]) {
				check[next] = true;
				q.push(next);
			}
		}
	}

	return count == total_islands;
}

void solution() {
	memset(visited, false, sizeof(visited));

	give_island_seq_number();

	get_distance_between_islands();

	sort(edges.begin(), edges.end());
	mst();

	if (is_all_connected()) cout << answer;
	else cout << "-1";
}

int main() {
	input();
	solution();
	return 0;
}
