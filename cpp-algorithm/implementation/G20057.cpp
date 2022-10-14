#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

int N, answer = 0;
int sand[500][500];
bool visited[500][500];

// const int dx[] = {0, 1, 1, 1, 0, -1, -1, -1}; // Left LD Down DR Right...
// const int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};x
const int dx[] = {0, 1, 0, -1};
const int dy[] = {-1, 0, 1, 0};
const double rates[] = {0.05, 0.07, 0.07, 0.1, 0.1, 0.02, 0.02, 0.01, 0.01, 0.55};

const int fx[4][10] = {
    {0, -1, 1, -1, 1, -2, 2, -1, 1, 0}, // dir = Left일 때 alpha, 5%, 7%R, 7%L, 10%, 10%, 2%, 2%, 1%, 1%
    {2, 0, 0, 1, 1, 0, 0, -1, -1, 1}, // dir = Down
    {0, 1, -1, 1, -1, 2, -2, 1, -1, 0},
    {-2, 0, 0, -1, -1, 0, 0, 1, 1, -1}
};

const int fy[][10] = {
    {-2, 0, 0, -1, -1, 0, 0, 1, 1, -1},
    {0, -1, 1, -1, 1, -2, 2, -1, 1, 0},
    {2, 0, 0, 1, 1, 0, 0, -1, -1, 1},
    {0, 1, -1, 1, -1, 2, -2, 1, -1, 0}
};

void input() {
    memset(visited, false, sizeof(visited));
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> sand[i][j];
        }
    }
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= N);
}

vector<int> get_stage_count() {
    vector<int> stage_counts;
    int final_num = N - 1;
    int num = 0;
    while (num++ != final_num) {
        stage_counts.push_back(num);
        stage_counts.push_back(num);
    }
    stage_counts.push_back(final_num);
    return stage_counts;
}

void solution() {
    vector<int> counts = get_stage_count();
    int x = N / 2, y = N / 2; // dir=0 : Left에서 시작
    visited[x][y] = true;

    for (int i = 0; i < counts.size(); i++) {
        int stage_count = counts[i];
        int dir = i % 4;

        while (stage_count--) {
            x += dx[dir];
            y += dy[dir];

            int sand_amount = sand[x][y]; // y에 있는 모래양
            sand[x][y] = 0;

            int total_moved_amount = 0;
            for (int t = 0; t < 10; t++) {
                double rate = rates[t];
                int nx = x + fx[dir][t];
                int ny = y + fy[dir][t];

                int moved_amount = t == 9 ? sand_amount - total_moved_amount : (int) (rate * sand_amount);

                if (in_range(nx, ny)) {
                    sand[nx][ny] += moved_amount;
                } else {
                    answer += moved_amount;
                }
                total_moved_amount += moved_amount;
            }
        }
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}