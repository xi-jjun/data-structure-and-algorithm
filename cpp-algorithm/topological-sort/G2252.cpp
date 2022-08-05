#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

int N, M;
int in_degree[32001];
vector<int> graph[32001];

void solution() {
	queue<int> q;
	for (int i = 1; i <= N; i++) {
		if (in_degree[i] == 0) {
			q.push(i);
		}
	}
	
	while (!q.empty()) {
		int now = q.front();
		q.pop();

		cout << now << ' ';

		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now][i];
			in_degree[next]--;
			if (in_degree[next] == 0) {
				q.push(next);
			}
		}
	}
}

void get_input() {
	int A, B;
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		cin >> A >> B;
		graph[A].push_back(B);
		in_degree[B]++;
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
