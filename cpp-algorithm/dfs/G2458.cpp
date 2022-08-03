#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

const bool UPPER = true;
const bool LOWER = false;
int N, M, cnt;
bool visited[501];
vector<pair<int, bool>> graph[501];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	int a, b;
	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		graph[a].push_back({b, true});
		graph[b].push_back({a, false});
	}
}

void dfs(int now, bool dir) {
	cnt++;
	visited[now] = true;
	for (int i = 0; i < graph[now].size(); i++) {
		int next = graph[now][i].first;
		bool condition = graph[now][i].second;

		if (condition == dir && !visited[next]) {
			dfs(next, dir);
		}
	}
}

int solution() {
	int answer = 0;
	for (int student = 1; student <= N; student++) {
		cnt = 0;
		memset(visited, false, sizeof(visited));
		dfs(student, UPPER);
		memset(visited, false, sizeof(visited));
		dfs(student, LOWER);

		if (cnt == N + 1) answer++;
	}

	return answer;
}

int main() {
	get_input();
	int answer = solution();
	cout << answer;
	return 0;
}
