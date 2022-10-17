#include <iostream>
#include <cstring>
#include <tuple>
#include <queue>
#include <algorithm>

#define AREA_1 1
#define AREA_2 2
#define AREA_3 3
#define AREA_4 4
#define AREA_5 5

using namespace std;

const int dx[] = {1, 1, 1, 0, -1, -1, -1, 0};
const int dy[] = {-1, 0, 1, 1, 1, 0, -1, -1};

int N, answer = 987654321;
int map[21][21]; // 구역정보
int A[21][21]; // 주어지는 인구
bool counted[21][21];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> A[i][j];
        }
    }
}

int max(int a, int b) { return a > b ? a : b; }

int min(int a, int b) { return a > b ? b : a; }

void init() {
    memset(map, 0, sizeof(map));
    memset(counted, false, sizeof(counted));
}

bool in_range(int x, int y) {
    return !(x < 1 || x > N || y < 1 || y > N);
}

bool valid(int x, int y, int d1, int d2) {
    return x + d1 + d2 <= N && y - d1 >= 1 && y + d2 <= N;
}

void seperate(pair<int, int> start, pair<int, int> end, int AREA) {
    int sx, sy, ex, ey;
    tie(sx, ex) = start;
    tie(sy, ey) = end;

    for (int i = sx; i < ex; i++) {
        for (int j = sy; j < ey; j++) {
            if ((AREA == AREA_1 || AREA == AREA_3) && map[i][j] == AREA_5) break;
            else if (AREA == AREA_2 && map[i][j] == AREA_5) continue;
            else if (AREA == AREA_4 && map[i][j] != 0) continue;

            map[i][j] = AREA;
        }
    }
}

void check_inner_area5(int x, int y) {
    queue<pair<int, int>> q;
    q.push({x, y});
    map[x][y] = AREA_5;

    while (!q.empty()) {
        tie(x, y) = q.front(); q.pop();

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[2 * d + 1];
            int ny = y + dy[2 * d + 1];

            if (in_range(nx, ny) && map[nx][ny] != AREA_5) {
                map[nx][ny] = AREA_5;
                q.push({nx, ny});
            }
        }
    }
}

void check_AREA_5(int x, int y, int d1, int d2) {
    int ox = x, oy = y;
    map[x][y] = AREA_5;
    for (int d = 0; d < 4; d++) {
        int cnt = d % 2 == 0 ? d1 : d2;
        
        for (int i = 0; i < cnt; i++) {
            x += dx[2 * d];
            y += dy[2 * d];
            map[x][y] = AREA_5;
        }
    }

    int ddx[] = {1, 0, -1, 0};
    int ddy[] = {0, 1, 0, -1};

    tie(x, y) = tie(ox, oy);
    for (int d = 0; d < 4; d++) {
        int cnt = d % 2 == 0 ? d1 : d2;
        
        for (int i = 0; i < cnt; i++) {
            check_inner_area5(x + ddx[d], y + ddy[d]);
            x += dx[2 * d];
            y += dy[2 * d];
        }
    }
}

void seperate_area(int x, int y, int d1, int d2) {
    check_AREA_5(x, y, d1, d2);
    seperate({1, x + d1}, {1, y + 1}, AREA_1);
    seperate({1, x + d2 + 1}, {y + 1, N + 1}, AREA_2);
    seperate({x + d1, N + 1}, {1, y - d1 + d2}, AREA_3);
    seperate({x + d2 + 1, N + 1}, {y - d1 + d2, N + 1}, AREA_4);
}

int count_population(int x, int y) {
    int ret = A[x][y];

    counted[x][y] = true;

    int AREA = map[x][y];
    queue<pair<int, int>> q;
    q.push({x, y});

    while (!q.empty()) {
        tie(x, y) = q.front(); q.pop();

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[2 * d + 1];
            int ny = y + dy[2 * d + 1];

            if (in_range(nx, ny) && map[nx][ny] == AREA && !counted[nx][ny]) {
                counted[nx][ny] = true;
                ret += A[nx][ny];
                q.push({nx, ny});
            }
        }
    }

    return ret;
}

void count_area_population() {
    int population[6];
    memset(population, 0, sizeof(population));

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (counted[i][j]) continue;
            int area = map[i][j];
            population[area] = count_population(i, j);
        }
    }

    sort(population, population + 6);
    int diff = population[5] - population[1];
    answer = min(answer, diff);
}

void process(int x, int y, int d1, int d2) {
    seperate_area(x, y, d1, d2);
    count_area_population();
}

void select_d(int x, int y) {
    for (int d1 = 1; d1 < N; d1++) {
        for (int d2 = 1; d2 < N; d2++) {
            if (valid(x, y, d1, d2)) {
                init();
                process(x, y, d1, d2);
            }
        }
    }
}

void solution() {
    for (int x = 1; x < N; x++) {
        for (int y = 2; y < N; y++) {
            select_d(x, y);
        }
    }
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}