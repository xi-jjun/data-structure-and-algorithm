// https://comyoung.tistory.com/279
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int dx[8] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dy[8] = {0, 1, 1, 1, 0, -1, -1, -1};
int N, low_ptr, high_ptr;
pair<int, int> post_office;
int house;
char map[51][51];
int height[51][51];
bool visited[51][51];
vector<int> heights;


void get_distinct_and_sorted_heights() {
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			heights.push_back(height[x][y]);
		}
	}
	
	sort(heights.begin(), heights.end());
	heights.erase(unique(heights.begin(), heights.end()), heights.end());
}


bool in_range(int x, int y) {
	return !(x < 0 || x >= N || y < 0 || y >= N);
}


void reset_visited() {
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			visited[x][y] = false;
		}
	}
}


bool bfs(int low_ptr, int high_ptr) {
	reset_visited();
	queue<pair<int, int> > q;
	int start_x = post_office.first;
	int start_y = post_office.second;
	if (height[start_x][start_y] >= heights[low_ptr] && height[start_x][start_y] <= heights[high_ptr]) {
		q.push(make_pair(start_x, start_y));
		visited[start_x][start_y] = true;
	}

	int house_cnt = house;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int d = 0; d < 8; d++) {
			int next_x = x + dx[d];
			int next_y = y + dy[d];

			if(!in_range(next_x, next_y) || visited[next_x][next_y]) continue;

			if (height[next_x][next_y] >= heights[low_ptr] && height[next_x][next_y] <= heights[high_ptr]) {
				visited[next_x][next_y] = true;
				q.push(make_pair(next_x, next_y));
				if (map[next_x][next_y] == 'K') house_cnt--; 
			}
		}
	}

	return house_cnt == 0;
}


int solution() {
	get_distinct_and_sorted_heights();
	
	int answer = 987654321;
	int low_ptr = 0;
	int high_ptr = 0;
	while (low_ptr < heights.size()) {
		if (bfs(low_ptr, high_ptr)) {
			answer = min(answer, heights[high_ptr] - heights[low_ptr]);
			low_ptr++;
		} else {
			if (high_ptr < heights.size() - 1) high_ptr++;
			else break;
		}
	}

	return answer;
}


void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			cin >> map[x][y];
			if (map[x][y] == 'P') {
				post_office.first = x;
				post_office.second = y;
			} else if (map[x][y] == 'K') {
				house++;
			}
		}
	}

	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			cin >> height[x][y];
		}
	}
}

int main() {
	get_input();

	int answer = solution();
	cout << answer;
	return 0;
}	
