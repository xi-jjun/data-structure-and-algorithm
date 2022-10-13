#include <iostream>
#include <cstring>

#define NONE 0
#define SHARK -1
#define ALIVE true
#define DEAD false

using namespace std;

struct Fish {
    bool alive;
    int direction;
    int x, y;
};

const int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1}; // Up LU Left LD Down RD Right RU
const int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};

int N = 4, answer, ans;
Fish fishes[17];
int map[4][4];

int max(int a, int b) { return a > b ? a : b; }

void input() {
    int fish_id, dir;
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    for (int row = 0; row < N; row++) {
        for (int col = 0; col < N; col++) {
            cin >> fish_id >> dir;
            map[row][col] = fish_id;
            fishes[fish_id] = {ALIVE, dir - 1, row, col};
        }
    }
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= 4 || y < 0 || y >= 4);
}

void move_fish(int map_[][4], Fish fishes_[]) {
    for (int fish_id = 1; fish_id <= 16; fish_id++) {
        if (fishes_[fish_id].alive) {
            int dir = fishes_[fish_id].direction;
            int x = fishes_[fish_id].x;
            int y = fishes_[fish_id].y;

            for (int r = 0; r < 8; r++) {
                int nx = x + dx[(dir + r) % 8];
                int ny = y + dy[(dir + r) % 8];

                if (in_range(nx, ny) && map_[nx][ny] != SHARK) {
                    int next = map_[nx][ny];
                    fishes_[fish_id].direction = (dir + r) % 8;

                    if (next == NONE) {
                        map_[x][y] = NONE;
                        map_[nx][ny] = fish_id;
                        fishes_[fish_id].x = nx;
                        fishes_[fish_id].y = ny;
                    } else { // 다른 물고기 존재
                        // swap
                        fishes_[fish_id].x = nx;
                        fishes_[fish_id].y = ny;
                        fishes_[next].x = x;
                        fishes_[next].y = y;
                        map_[x][y] = next;
                        map_[nx][ny] = fish_id;
                    }
                    break;
                }
            }
        }
    }
}

void copy_fishes(Fish temp[], Fish fishes[]) {
    for (int i = 1; i <= 16; i++) {
        temp[i] = fishes[i];
    }
}


void move_fish_and_shark(int x, int y, int dir, int acc, int map_[][4], Fish fishes_[]) {
    int temp_map[4][4]; // 현재 단계에서 사용할 임시 map
    Fish temp_fishes[17]; // 현재 단계에서 사용할 임시 fishes정보
    copy_fishes(temp_fishes, fishes_);
    memcpy(temp_map, map_, 4 * 4 * sizeof(int));
    
    move_fish(temp_map, temp_fishes);

    for (int jump = 1; jump < 4; jump++) {
        int nx = x + jump * dx[dir];
        int ny = y + jump * dy[dir];

        if (in_range(nx, ny) && temp_map[nx][ny] != NONE) {
            int next_fish_id = temp_map[nx][ny];
            int next_fish_dir = temp_fishes[next_fish_id].direction;
            temp_map[x][y] = NONE;
            temp_map[nx][ny] = SHARK;
            temp_fishes[next_fish_id].alive = DEAD;

            move_fish_and_shark(nx, ny, next_fish_dir, acc + next_fish_id, temp_map, temp_fishes);
            answer = max(answer, acc + next_fish_id);

            temp_map[x][y] = SHARK;
            temp_map[nx][ny] = next_fish_id;
            temp_fishes[next_fish_id].alive = ALIVE;
        }
    }
}

void solution() {
    answer += map[0][0];
    int fish_id = map[0][0];
    int start_direction = fishes[fish_id].direction;
    fishes[fish_id].alive = DEAD;
    map[0][0] = SHARK;

    move_fish_and_shark(0, 0, start_direction, fish_id, map, fishes);

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}