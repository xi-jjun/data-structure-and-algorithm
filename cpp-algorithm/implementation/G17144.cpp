#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

const int dx[] = {-1, 0, 1, 0};
const int dy[] = {0, 1, 0, -1};

int N, M, T;
int dust[51][51];
vector<int> purifier;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    memset(dust, 0, sizeof(dust));

    cin >> N >> M >> T;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> dust[i][j];
            if (dust[i][j] == -1) purifier.push_back(i);
        }
    }
}

bool in_range(int x, int y) {
    return !(x < 1 || x > N || y < 1 || y > M);
}

bool is_purifier(int x, int y) {
    return dust[x][y] == -1;
} 

bool can_be_spreaded(int x, int y) {
    return in_range(x, y) && !is_purifier(x, y);
}

void spread_dust() {
    int new_dust[51][51];
    memset(new_dust, 0, sizeof(new_dust));
    // memcpy(new_dust, dust, 51 * 51 * sizeof(int));

    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            if (dust[x][y] <= 0) continue;

            int spreaded_amount = dust[x][y] / 5;
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (can_be_spreaded(nx, ny)) {
                    new_dust[nx][ny] += spreaded_amount;
                    count++;
                } 
            } 
            new_dust[x][y] += dust[x][y] - spreaded_amount * count;
        }
    }
    memcpy(dust, new_dust, 51 * 51 * sizeof(int));
    dust[purifier[0]][1] = -1;
    dust[purifier[1]][1] = -1;
}

void clean_reverse_clockwise() {
    for (int row = purifier[0] - 1; row > 1; row--) {
        dust[row][1] = dust[row - 1][1];
    }

    for (int col = 1; col < M; col++) {
        dust[1][col] = dust[1][col + 1];
    }

    for (int row = 1; row < purifier[0]; row++) {
        dust[row][M] = dust[row + 1][M];
    }

    for (int col = M; col > 2; col--) {
        dust[purifier[0]][col] = dust[purifier[0]][col - 1];
    }
    dust[purifier[0]][2] = 0;
}

void clean_clockwise() {
    for (int row = purifier[1] + 1; row < N; row++) {
        dust[row][1] = dust[row + 1][1];
    }

    for (int col = 1; col < M; col++) {
        dust[N][col] = dust[N][col + 1];
    }

    for (int row = N; row > purifier[1]; row--) {
        dust[row][M] = dust[row - 1][M];
    }

    for (int col = M; col > 2; col--) {
        dust[purifier[1]][col] = dust[purifier[1]][col - 1];
    }
    dust[purifier[1]][2] = 0;
}

void cleaning_dust() {
    clean_reverse_clockwise();
    clean_clockwise();
}

int count_dust() {
    int ret = 0;
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            if (dust[x][y] > 0) ret += dust[x][y];
        }
    }
    return ret;
}

void solution() {
    while (T--) {
        spread_dust();
        cleaning_dust();
    }

    int answer = count_dust();
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}