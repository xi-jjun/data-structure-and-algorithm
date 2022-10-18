#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>

#define WHITE 0
#define RED 1
#define BLUE 2

using namespace std;

const int dx[] = {0, 0, -1, 1};
const int dy[] = {1, -1, 0, 0};

int N, K;
int color[13][13];
vector<int> chess[13][13];
tuple<int, int, int> horses[11];

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N >> K;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> color[i][j];
        }
    }

    int x, y, dir;
    for (int h = 1; h <= K; h++) {
        cin >> x >> y >> dir;
        horses[h] = {x, y, dir - 1};
        chess[x][y].push_back(h);
    }
}

bool in_range(int x, int y) {
    return !(x < 1 || x > N || y < 1 || y > N);
}

int reversed_dir(int d) {
    switch (d) {
        case 0: return 1;
        case 1: return 0;
        case 2: return 3;
        case 3: return 2;
        default:
        return -1;
    }
}

vector<int> get_moved_horses(int h, int x, int y) {
    int idx = 0;

    for (auto horse : chess[x][y]) {
        if (horse == h) break;
        idx++;
    }

    auto moved_first = chess[x][y].begin() + idx;
    auto moved_last = chess[x][y].end();
    auto remained_first = chess[x][y].begin();

    vector<int> moved_horses(moved_first, moved_last);

    vector<int> remained_horses(remained_first, moved_first);
    chess[x][y].clear();
    chess[x][y] = remained_horses;

    return moved_horses;
}

int count_horses() {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (chess[i][j].size() >= 4) return 4;
        }
    }
    return 1;
}

void move_to_next(tuple<int, int, int, int> next_horse_info, vector<int> moved_horses) {
    int nx, ny, dir, h;
    tie(h, nx, ny, dir) = next_horse_info;

    for (auto moved_horse : moved_horses) {
        if (moved_horse == h) {
            horses[moved_horse] = {nx, ny, dir};
        } else {
            horses[moved_horse] = {nx, ny, get<2>(horses[moved_horse])};
        }
        chess[nx][ny].push_back(moved_horse);
    }
}

void move_next(tuple<int, int, int, int> horse_info, bool reversed) {
    int x, y, dir, h;
    tie(h, x, y, dir) = horse_info;

    int nx = x + dx[dir];
    int ny = y + dy[dir];

    if (!in_range(nx, ny)) {
        dir = reversed_dir(dir);
        move_next({h, x, y, dir}, true);
    } else if (color[nx][ny] == BLUE) {
        if (reversed) {
            horses[h] = {x, y, dir};
            return;
        }
        dir = reversed_dir(dir);
        move_next({h, x, y, dir}, true);
    } else if (color[nx][ny] == WHITE) {
        vector<int> moved_horses = get_moved_horses(h, x, y);
        move_to_next({h, nx, ny, dir}, moved_horses);
    } else if (color[nx][ny] == RED) {
        vector<int> moved_horses = get_moved_horses(h, x, y);
        reverse(moved_horses.begin(), moved_horses.end());
        move_to_next({h, nx, ny, dir}, moved_horses);
    }
}

bool move_horses() {
    int x, y, dir;
    for (int h = 1; h <= K; h++) {
        tie(x, y, dir) = horses[h];
        tuple<int, int, int, int> horse_info = {h, x, y, dir};

        move_next(horse_info, false);

        if (count_horses() >= 4) return true; 
    }

    return count_horses() >= 4;
}

void solution() {
    int turn = 0;
    bool end = false;

    while (!end && turn <= 1000) {
        end = move_horses();
        turn++;
    }

    if (turn > 1000) cout << "-1";
    else cout << turn;
}

int main() {
    input();
    solution();
    return 0;
}