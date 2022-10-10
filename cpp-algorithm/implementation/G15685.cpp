// https://donggoolosori.github.io/2020/11/20/boj-15685/
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>

#define MAX_ 101

using namespace std;

struct Dragon_curve {
    int start_x;
    int start_y;
    string directions;
    int start_direction;
    int gen;
};

const int dy[] = {0, -1, 0, 1}; // R U L Down
const int dx[] = {1, 0, -1, 0};
const char dir[] = {'0', '1', '2', '3'};

int N, answer = 0;
int map_[102][102];
Dragon_curve curve[21];

void input() {
    memset(map_, 0, sizeof(map_));
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int x, y, d, g;
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> x >> y >> d >> g;
        curve[i] = {x, y, "", d, g};
        map_[x][y] = 1;
    }
}

string rotate_left_90(char d) {
    return string(1, dir[(d - '0' + 1) % 4]);
}

string reverse_and_rotate(string directions) {
    string ret = "";
    reverse(directions.begin(), directions.end());
    for (auto dir : directions) {
        ret += rotate_left_90(dir);
    }
    return ret;
}

void make_curve(int curve_idx) {
    Dragon_curve dc = curve[curve_idx];
    int generation = dc.gen;
    dc.directions += dir[dc.start_direction]; // 1세대 방향 정보 삽입

    while (generation-- > 0) {
        string converted_dir = reverse_and_rotate(dc.directions);
        dc.directions += converted_dir;
    }
    curve[curve_idx].directions = dc.directions;
}

bool is_square(int x, int y) {
    return map_[x][y] == 1 && map_[x + 1][y] == 1 && map_[x][y + 1] == 1 && map_[x + 1][y + 1] == 1;
}

void count_square() {
    for (int i = 0; i < MAX_; i++) {
        for (int j = 0; j < MAX_; j++) {
            if (is_square(i, j)) answer++;
        }
    }
}

void solution() {
    for (int i = 0; i < N; i++) {
        make_curve(i); // 방향 정보 구하기

        string directions = curve[i].directions;
        int x = curve[i].start_x;
        int y = curve[i].start_y;
        
        for (int j = 0; j < directions.length(); j++) {
            int d = directions[j] - '0';

            x += dx[d];
            y += dy[d];
            map_[x][y] = 1;
        }
    }

    count_square();

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}