#include <iostream>

using namespace std;

struct Shark {
    bool existed;
    int size;
    int speed;
    int direction;
    // int x, y;
};

const int dx[] = {-99999, -1, 1, 0, 0}; // U D R L
const int dy[] = {-99999, 0, 0, 1, -1};
const Shark NOT_EXISTED = {false, 0, 0, 0};

int N, M, S, answer = 0;
Shark map[101][101];

void init() {
    for (int x = 1; x <= 100; x++) {
        for (int y = 1; y <= 100; y++) {
            map[x][y] = NOT_EXISTED;
        }
    }
}

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int r, c, s, d, z;

    cin >> N >> M >> S;
    init();
    for (int i = 1; i <= S; i++) {
        cin >> r >> c >> s >> d >> z;
        map[r][c] = {true, z, s, d};
    }
}

void catch_shark(int king_loc) {
    int row = 1;
    while (!map[row][king_loc].existed) {
        if (++row > N) break;
    }

    if (row <= N) {
        answer += map[row][king_loc].size;
        map[row][king_loc] = NOT_EXISTED;
    }
}

void move_shark() {
    Shark new_map[101][101];
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            new_map[x][y] = NOT_EXISTED;
        }
    } 
    
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            if (!map[x][y].existed) continue;

            int this_size = map[x][y].size;
            int speed = map[x][y].speed;
            int direction = map[x][y].direction;

            int nx = x + speed * dx[direction];
            int ny = y + speed * dy[direction];
            while (true) {
                if (ny < 1) {
                    direction = 3;
                    ny = 2 - ny;
                } else if (ny > M) {
                    direction = 4;
                    ny = 2 * M - ny;
                } else if (nx < 1) {
                    direction = 2;
                    nx = 2 - nx;
                } else if (nx > N) {
                    direction = 1;
                    nx = 2 * N - nx;
                } else break;
            }

            if (!new_map[nx][ny].existed || new_map[nx][ny].size < this_size) {
                new_map[nx][ny] = {true, this_size, speed, direction};
            } 
        }
    }

    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            map[x][y] = new_map[x][y];
        }
    } 
}

void solution() {
    for (int loc = 1; loc <= M; loc++) {
        catch_shark(loc);
        move_shark();
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}