#include <iostream>
#include <queue>
#include <set>

using namespace std;

const bool RED = true;
const bool BLUE = false;
const pair<int, int> IN_HOLE (-1, -1);
const int dx[4] = {-1, 0, 1, 0};
const int dy[4] = {0, 1, 0, -1};
int N, M;
char map[11][11];
pair<int, int> red_start;
pair<int, int> blue_start;
priority_queue<pair<int, pair< pair<int, int>, pair<int, int> >>> pq;
set<pair<pair<int, int>, pair<int, int>>> visited;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
			if (map[i][j] == 'R') {
				red_start = {i, j};
				map[i][j] = '.';
			} else if (map[i][j] == 'B') {
				blue_start = {i, j};
				map[i][j] = '.';
			}
		}
	}
}

pair<pair<int, int>, int> move_ball(int dir, pair<int, int> start, bool color) {
	int x = start.first;
	int y = start.second;
	int move_cnt = 0;

	while (true) {
		move_cnt++;
		x += dx[dir];
		y += dy[dir];
		if (map[x][y] == '#') {
			x -= dx[dir];
			y -= dy[dir];
			break;
		} else if (map[x][y] == 'O') {
			if (color == RED) return make_pair(IN_HOLE, -1);
			if (color == BLUE) return make_pair(IN_HOLE, -2);
		}
	}

	return {{x, y}, move_cnt};
}

bool is_visited(pair<int, int> red, pair<int, int> blue) {
	return visited.find({red, blue}) != visited.end();
}

int simulation() {
	visited.insert({red_start, blue_start});
	pq.push({0, {red_start, blue_start}});

	while (!pq.empty()) {
		int count = -pq.top().first;
		pair<int, int> red = pq.top().second.first;
		pair<int, int> blue = pq.top().second.second;
		pq.pop();

		if (count >= 10) return -1;
		
		for (int i = 0; i < 4; i++) {
			pair<pair<int, int>, int> next_red = move_ball(i, red, RED);
			pair<pair<int, int>, int> next_blue = move_ball(i, blue, BLUE);

			if (next_blue.first == IN_HOLE) {
				continue;
			} else if (next_red.first == IN_HOLE) {
				return count + 1;
			} else {
				if (next_red.first == next_blue.first && next_red.second > next_blue.second) {
					next_red.first.first -= dx[i];
					next_red.first.second -= dy[i];
				} else if (next_red.first == next_blue.first && next_red.second < next_blue.second) {
					next_blue.first.first -= dx[i];
					next_blue.first.second -= dy[i];
				}

				if (is_visited(next_red.first, next_blue.first)) continue;
				visited.insert({next_red.first, next_blue.first});
				pq.push({-(count + 1), {next_red.first, next_blue.first}});
			}
		}
	}
	return -1;
}

int main() {
	input();
	int answer = simulation();
	cout << answer;
	return 0;
}
