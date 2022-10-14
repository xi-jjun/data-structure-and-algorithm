#include <iostream>
#include <queue>
#include <tuple>
#include <cstring>

#define MAX_ 100000000
#define ACTIVATED_VIRUS -1
#define EMPTY 0
#define WALL 1
#define DEACTIVATED_VIRUS 2

using namespace std;

const int dx[] = {-1, 0, 1, 0};
const int dy[] = {0, 1, 0, -1};

int N, M, answer = MAX_;
int total_virus_cnt = 0;
int map[51][51];
pair<int, int> viruses[11];
pair<int, int> selected[11];
bool used[11] = {false};

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M;
    int idx = 0;
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            cin >> map[x][y];
            if (map[x][y] == DEACTIVATED_VIRUS) {
                viruses[idx++] = {x, y};
                total_virus_cnt++;
            }
        }
    }
}

int max(int a, int b) { return a > b ? a : b; }

int min(int a, int b) { return a < b ? a : b; }

void present_activated_viruses(int new_map[][51], queue<tuple<int, int, int>> &q, bool visited[][51]) {
    for (int i = 0; i < M; i++) {
        pair<int, int> coord = selected[i];
        q.push({coord.first, coord.second, 0});
        visited[coord.first][coord.second] = true;
        new_map[coord.first][coord.second] = ACTIVATED_VIRUS;
    }
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= N);
}

int spread_viruses() {
    int time[51][51];
    int new_map[51][51];
    bool visited[51][51];
    memset(time, 0, sizeof(time));
    memset(visited, false, sizeof(visited));
    memcpy(new_map, map, 51 * 51 * sizeof(int));

    queue<tuple<int, int, int>> q; // (x, y, time)

    present_activated_viruses(new_map, q, visited); // => 여기서 visited 표시를 안해줬기 때문에 틀렸던 것.

    while (!q.empty()) {
        int now_x, now_y, now_time;
        tie(now_x, now_y, now_time) = q.front(); q.pop();

        for (int d = 0; d < 4; d++) {
            int nx = now_x + dx[d];
            int ny = now_y + dy[d];

            if (in_range(nx, ny) && !visited[nx][ny] && new_map[nx][ny] != WALL) {
                visited[nx][ny] = true;
                time[nx][ny] = now_time + 1;
                q.push({nx, ny, time[nx][ny]});
            }
        }
    }

    int ret = 0;
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            if (new_map[x][y] == WALL || new_map[x][y] == DEACTIVATED_VIRUS) continue;
            else if (new_map[x][y] == EMPTY && !visited[x][y]) return -1;

            ret = max(ret, time[x][y]);
        }
    }

    return ret;
}

void choose_viruses(int depth, int at) {
    if (depth == M) {
        int ans = spread_viruses();
        answer = ans != -1 ? min(ans, answer) : min(MAX_, answer);
        return;
    }

    for (int i = at; i < total_virus_cnt; i++) {
        if (!used[i]) {
            used[i] = true;
            selected[depth] = viruses[i];
            choose_viruses(depth + 1, i + 1);
            used[i] = false;
        }
    }
}

void solution() {
    choose_viruses(0, 0);

    if (answer == MAX_) cout << "-1";
    else cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}