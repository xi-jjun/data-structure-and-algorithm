#include <iostream>
#include <algorithm>
#include <cstring>

#define INF 1000000000000000
#define GRASS 0
#define GRAVE_STONE 2
#define GHOST_HOLE 3

typedef long long ll;
using namespace std;

const int dx[4] = {-1, 0, 1, 0};
const int dy[4] = {0, 1, 0, -1};

int W, H, G, E;
int x, y, x1, y1, x2, y2, T;
int cemetry[31][31];
ll dp[31][31];
pair<pair<int, int>, ll> ghost_holes[31][31];

void reset() {
	memset(cemetry, 0, sizeof(cemetry));
	for (int i = 0; i <= W; i++) {
		for (int j = 0; j <= H; j++) {
			ghost_holes[i][j] = {{-1, -1}, -1};
		}
	}
	fill(&dp[0][0], &dp[30][30], INF);
}

bool input() {
	cin >> W >> H;
	if (W == 0 && H == 0) return false;

	reset();
	cin >> G;
	for (int i = 0; i < G; i++) {
		cin >> x >> y;
		cemetry[x][y] = GRAVE_STONE;
	}

	cin >> E;
	for (int i = 0; i < E; i++) {
		cin >> x1 >> y1 >> x2 >> y2 >> T;
		ghost_holes[x1][y1] = {{x2, y2}, T};
		cemetry[x1][y1] = GHOST_HOLE;
	}

	return true;
}

bool in_range(int x, int y) {
	return !(x < 0 || x >= W || y < 0 || y >= H);
}

bool is_end(int x, int y) {
	return x == W - 1 && y == H - 1;
}

bool search_all_edges(int stage) {
	for (int now_x = 0; now_x < W; now_x++) {
		for (int now_y = 0; now_y < H; now_y++) {
			if (dp[now_x][now_y] == INF || cemetry[now_x][now_y] == GRAVE_STONE || is_end(now_x, now_y)) {
				continue;
			}

			if (cemetry[now_x][now_y] == GHOST_HOLE) {
				int next_x = ghost_holes[now_x][now_y].first.first;
				int next_y = ghost_holes[now_x][now_y].first.second;
				ll time = ghost_holes[now_x][now_y].second;

				if (dp[next_x][next_y] > dp[now_x][now_y] + time) {
					dp[next_x][next_y] = dp[now_x][now_y] + time;
					if (stage == -1) return true;
				}
			} else if (cemetry[now_x][now_y] == GRASS) {
				for (int d = 0; d < 4; d++) {
					int next_x = now_x + dx[d];
					int next_y = now_y + dy[d];

					if (in_range(next_x, next_y) && cemetry[next_x][next_y] != GRAVE_STONE && dp[next_x][next_y] > dp[now_x][now_y] + 1) {
						dp[next_x][next_y] = dp[now_x][now_y] + 1;
						if (stage == -1) return true;
					}
				}
			}
		}
	}

	return false;
}

bool bellman_ford(int start_x, int start_y) {
	dp[start_x][start_y] = 0;
	
	for (int stage = 0; stage < W * H; stage++) {
		search_all_edges(stage);
	}

	return search_all_edges(-1);
}

void solution() {
	while (input()) {
		bool neg_cycles =  bellman_ford(0, 0);
		if (neg_cycles) cout << "Never\n";
		else if (dp[W - 1][H - 1] == INF) cout << "Impossible\n";
		else cout << dp[W - 1][H - 1] << "\n";
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);	

	solution();

	return 0;
}
