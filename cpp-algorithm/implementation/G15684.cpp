#include <iostream>
#include <vector>
#include <cstring>

#define DISCONNECTED false
#define CONNECTED true
#define MAX_ 100000000

using namespace std;

int N, M, H, answer;
bool ladder[32][12][12];
// vector<vector<vector<bool>>> ladder(32, vector<vector<bool>> (12, vector<bool> (12, DISCONNECTED))); 

int min(int a, int b) { return a > b ? b : a; }

void input() {
    memset(ladder, false, sizeof(ladder));
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int row, src;
    cin >> N >> M >> H;
    for (int i = 0; i < M; i++) {
        cin >> row >> src;
        ladder[row][src][src + 1] = CONNECTED;
        ladder[row][src + 1][src] = CONNECTED;
    }
}

bool in_range(int row, int src) {
    return 1 <= row && row <= H && 1 <= src && src <= N;
}

bool is_connected(int row, int src) {
    return in_range(row, src) && ladder[row][src][src + 1];
}

bool is_self_result() {
    for (int line = 1; line <= N; line++) {
        int curr_line = line;
        for (int row = 1; row <= H; row++) {
            if (ladder[row][curr_line][curr_line + 1]) curr_line++;
            else if (ladder[row][curr_line - 1][curr_line]) curr_line--;
            // if (is_connected(row, curr_line)) curr_line++;
            // else if (is_connected(row, curr_line - 1)) curr_line--;
        }

        if (curr_line != line) return false;
    }

    return true;
}

void put_ladder(int depth, int now_row) {
    if (depth >= 4) return;
    else if (is_self_result()) {
        answer = min(answer, depth);
        return;
    }

    for (int row = now_row; row <= H; row++) {
        for (int col = 1; col < N; col++) {
            if (ladder[row][col][col + 1] || ladder[row][col - 1][col] || ladder[row][col + 1][col + 2]) continue;
            // if (is_connected(row, col) || is_connected(row, col + 1) || is_connected(row, col - 1)) {
            //     continue;
            // }

            ladder[row][col][col + 1] = CONNECTED;
            ladder[row][col + 1][col] = CONNECTED;

            put_ladder(depth + 1, row);

            ladder[row][col][col + 1] = DISCONNECTED;
            ladder[row][col + 1][col] = DISCONNECTED;
        }
    }
}

void solution() {
    answer = MAX_;
    put_ladder(0, 1);

    if (answer == MAX_) cout << "-1";
    else cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}