#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
const int INF = 100000001;
int map[101][101];

void init() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i == j) map[i][j] = 0;
			else map[i][j] = INF;
		}
	}
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int a, b, c;
	cin >> N >> M;
	init();
	for (int i = 0; i < M; i++) {
		cin >> a >> b >> c;
		map[a][b] = min(map[a][b], c);
	}
}

void solution() {
	for (int via = 1; via <= N; via++) {
		for (int a = 1; a <= N; a++) {
			for (int b = 1; b <= N; b++) {
				map[a][b] = min(map[a][via] + map[via][b], map[a][b]);
			}
		}
	}

	for (int a = 1; a <= N; a++) {
		for (int b = 1; b <= N; b++) {
			cout << (map[a][b] == INF ? 0 : map[a][b]) << ' ';
		}
		cout << "\n";
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
