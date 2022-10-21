// 2시간 10분 소요
#include <iostream>
#include <cstring>
#include <tuple>

using namespace std;

struct Shark {
    bool alive;
    int x, y;
    int dir;
};

const bool NO_SMELL = false;
const bool SMELL = true;
const bool ALIVE = true;
const bool DEAD = false;
const int dx[] = {-1, 1, 0, 0}; // Up Down Left Right
const int dy[] = {0, 0, -1, 1};

int N, M, k;
int smell[21][21]; // (x, y) 에 냄새를 남긴 상어 번호
int remained_time[21][21]; // (x, y) 에 냄새가 없어질 시간
int map[21][21]; 
int p_dir[500][4][4];
Shark sharks_info[500];

void init() {
    memset(map, 0, sizeof(map));
    memset(smell, 0, sizeof(smell));
    memset(remained_time, 0, sizeof(remained_time));
}

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    init();

    cin >> N >> M >> k;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> map[i][j];
            if (map[i][j] != 0) {
                int shark_number = map[i][j];
                sharks_info[shark_number] = {ALIVE, i, j, -1};
            }
        }
    }

    for (int shark = 1; shark <= M; shark++) {
        int now_dir;
        cin >> now_dir;
        sharks_info[shark].dir = now_dir - 1;
    }

    for (int shark = 1; shark <= M; shark++) {
        for (int dir = 0; dir < 4; dir++) {
            int direction;

            for (int d = 0; d < 4; d++) {
                cin >> direction;
                p_dir[shark][dir][d] = direction - 1;
            }
        }
    }
}

// 1번 상어 말고 모두 없어져 있어야 한다.
bool is_end() {
    for (int i = 2; i <= M; i++) {
        if (sharks_info[i].alive) return false;
    }
    return true;
}

bool in_range(int x, int y) {
    return !(x < 1 || x > N || y < 1 || y > N);
}

void smelling() {
    for (int s = 1; s <= M; s++) {
        Shark shark = sharks_info[s];
        if (shark.alive) {
            int x = shark.x;
            int y = shark.y;
            smell[x][y] = s; // s번 상어가 (x, y)에 냄새를 남겼다.
            remained_time[x][y] = k; // k 초 후에 냄새는 사라진다.
        }
    }
}

bool is_empty_around(int x, int y) {
    for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (in_range(nx, ny) && smell[nx][ny] == 0) return true;
    }
    return false;
}

pair<int, int> move_shark(int shark_num, bool type) {
    int cmp = type == NO_SMELL ? 0 : shark_num;
    Shark shark = sharks_info[shark_num];
    int x = shark.x;
    int y = shark.y;
    int now_dir = shark.dir;

    for (int d = 0; d < 4; d++) {
        int dir = p_dir[shark_num][now_dir][d];
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (in_range(nx, ny) && smell[nx][ny] == cmp) {
            // 상어 정보 업데이트
            sharks_info[shark_num].x = nx;
            sharks_info[shark_num].y = ny;
            sharks_info[shark_num].dir = dir;

            return {nx, ny};
        }
    }
    return {-1, -1};
}

void move_all_sharks() {
    int new_map[21][21];
    memset(new_map, 0, sizeof(new_map));

    for (int s = 1; s <= M; s++) {
        Shark shark = sharks_info[s];
        if (shark.alive) {
            int nx, ny;
            bool type = is_empty_around(shark.x, shark.y) ? NO_SMELL : SMELL;

            pair<int, int> next = move_shark(s, type);
            tie(nx, ny) = next;

            if (new_map[nx][ny] == 0) new_map[nx][ny] = s;
            else {
                sharks_info[s].alive = DEAD;
            }
        }
    }

    memcpy(map, new_map, 21 * 21 * sizeof(int));
}

void decrease_smell_time() {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (remained_time[i][j] > 0) remained_time[i][j]--;
            if (remained_time[i][j] == 0) {
                smell[i][j] = 0; // 냄새 제거 됨.
            }
        }
    }
}

void solution() {
    int answer = 0;
    
    while (!is_end() && answer <= 1000) {
        smelling();
        move_all_sharks();
        decrease_smell_time();
        answer++;
    }

    if (answer > 1000) cout << "-1";
    else cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}