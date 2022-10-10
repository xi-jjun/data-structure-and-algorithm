#include <iostream>
#include <vector>

using namespace std;

#define UNDETECTED '0'
#define WALL '6'
#define DETECTED '#'

const int dx[] = {0, -1, 0, 1}; // R U L Down
const int dy[] = {1, 0, -1, 0};
int N, M, answer = 10000000;
// char office[9][9];
vector<vector<char>> office(21, vector<char> (21));

int min(int a, int b) { return a > b ? b : a; }

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> office[i][j];
        }
    }
}

bool is_cctv(int x, int y) {
    return '0' < office[x][y] && office[x][y] <= '5';
}

void counting_undetected_area(vector<vector<char>> area) {
    int ret = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (area[i][j] == UNDETECTED) ret++;
        }
    }
    answer = min(answer, ret);
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= M);
}

void go_straight(int direction, vector<vector<char>> &temp_office, int x, int y) {
    int nx = x + dx[direction];
    int ny = y + dy[direction];
    while (in_range(nx, ny) && temp_office[nx][ny] != WALL) {
        if (!is_cctv(nx, ny)) // CCTV는 통과 가능
            temp_office[nx][ny] = DETECTED;
        nx += dx[direction];
        ny += dy[direction];
    }
}

void check_to_office(int x, int y, int direction, char cctv, vector<vector<char>> &temp_office) {
    // CCTV type과 direction에 따라서 #을 temp_office에 표시해줘야 함
    switch (cctv) {
        case '1':
            go_straight(direction, temp_office, x, y);
            break;
        case '2':
            go_straight(direction, temp_office, x, y);
            go_straight((direction + 2) % 4, temp_office, x, y);
            break;
        case '3':
            go_straight(direction, temp_office, x, y);
            go_straight((direction + 3) % 4, temp_office, x, y);
            break;
        case '4':
            go_straight(direction, temp_office, x, y);
            go_straight((direction + 1) % 4, temp_office, x, y);
            go_straight((direction + 3) % 4, temp_office, x, y);
            break;
        case '5':
            for (int d = 0; d < 4; d++) go_straight(d, temp_office, x, y);
            break;
        default:
            return;
    }
}

void solution(int x, int y, vector<vector<char>> temp_office) {
    if (x == N) {
        counting_undetected_area(temp_office);
        return;
    } else if (!is_cctv(x, y)) {
        int next_x = (y + 1) % M == 0 ? x + 1 : x;
        int next_y = (y + 1) % M;

        solution(next_x, next_y, temp_office);
        return;
    }

    vector<vector<char>> temp = temp_office;
    for (int d = 0; d < 4; d++) {
        if (office[x][y] == '2' && d >= 2) continue;
        else if (office[x][y] == '5' && d >= 1) continue;
        check_to_office(x, y, d, office[x][y], temp);

        int next_x = (y + 1) % M == 0 ? x + 1 : x;
        int next_y = (y + 1) % M;

        solution(next_x, next_y, temp);
        temp = temp_office;
    }
}

int main() {
    input();
    vector<vector<char>> temp_office = office;
    solution(0, 0, temp_office);
    cout << answer;
    return 0;
}