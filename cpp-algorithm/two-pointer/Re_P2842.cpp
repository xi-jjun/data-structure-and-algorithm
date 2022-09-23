#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include <queue>
#include <cstring>

using namespace std;

const int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
const int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};

bool ok;
int N, home;
vector<int> heights;
char map[51][51];
int h[51][51];
pair<int, int> postoffice;

/*
    P로 다시 돌아오는걸 알아볼 필요가 없는 이유
    => 어차피 P->K 갔다는 것 자체가 다시 그 길로 돌아오면 되기 때문. 따라서 그냥 K를 다 지나칠 수 있는지만 보면 된다.
*/
void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> map[i][j];
            if (map[i][j] == 'P') postoffice = {i, j};
            else if (map[i][j] == 'K') home++;
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> h[i][j];
            heights.push_back(h[i][j]);
        }
    }
}

bool cmp(int a, int b) {
    return a < b;
}

void make_heigths() {
    set<int> dup;
    for (auto data : heights) dup.insert(data);
    heights.clear();
    set<int>::iterator iter;
    for (iter = dup.begin(); iter != dup.end(); iter++) {
        heights.push_back(*iter);
    }
    sort(heights.begin(), heights.end(), cmp);
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= N);
}

bool valid_h(int x, int y, int min_h, int max_h) {
    return h[x][y] >= min_h && h[x][y] <= max_h;
}

bool bfs(int min_idx, int max_idx) {
    int min_h = heights[min_idx];
    int max_h = heights[max_idx];

    if (!valid_h(postoffice.first, postoffice.second, min_h, max_h)) return false;

    bool visited[51][51];
    memset(visited, false, sizeof(visited));

    queue<pair<int, int>> q;
    q.push({postoffice.first, postoffice.second});
    visited[postoffice.first][postoffice.second] = true;

    int visited_home = 0;
    while (!q.empty()) {
        pair<int, int> p = q.front(); q.pop();
        int now_x = p.first;
        int now_y = p.second;

        for (int d = 0; d < 8; d++) {
            int next_x = now_x + dx[d];
            int next_y = now_y + dy[d];

            if (in_range(next_x, next_y) && !visited[next_x][next_y] && valid_h(next_x, next_y, min_h, max_h)) {
                visited[next_x][next_y] = true;
                if (map[next_x][next_y] == 'K') visited_home++;
                q.push({next_x, next_y});
            }
        }
    }

    return visited_home == home;
}

void solution() {
    make_heigths();
    int left = 0, right = 0;

    pair<int, int> answer = {0, 1000000000};
    while (right < heights.size()) {
        ok = bfs(left, right);

        if (ok) {
            if (answer.second - answer.first > heights[right] - heights[left]) answer = {heights[left], heights[right]};
            left++;
            if (left > right) swap(left, right);
        } else {
            right++;
        }
    }

    cout << (answer.second - answer.first);
}

int main() {
    input();
    solution();
    return 0;
}