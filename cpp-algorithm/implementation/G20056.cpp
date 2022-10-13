#include <iostream>
#include <vector>

using namespace std;

struct Fireball {
    int mass;
    int speed;
    int direction;
};

const int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
const int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};

int N, M, K;
vector<Fireball> map[51][51];

void input() {
    int x, y, mass, speed, direction;
    ios::sync_with_stdio(false); cin.tie(NULL); cin.tie(NULL);
    cin >> N >> M >> K;

    for (int i = 0; i < M; i++) {
        cin >> x >> y >> mass >> speed >> direction;
        map[x - 1][y - 1].push_back({ mass, speed, direction });
    }
}

int convert_loc(int loc) {
    if (loc < 0) {
        int k = 1;
        int kN = N;
        while (kN + loc < 0) {
            kN = (k + 1) * N;
            k++;
        }
        return (kN + loc) % N;
    }
    return loc % N;
}

pair<int, int> get_next_loc(int x, int y, Fireball fireball) {
    int speed = fireball.speed;
    int dir = fireball.direction;
    int nx, ny;

    nx = convert_loc(x + speed * dx[dir]);
    ny = convert_loc(y + speed * dy[dir]);

    return {nx, ny};
}

void move_fireballs() {
    vector<Fireball> new_map[51][51];
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            if (map[x][y].size() == 0) continue;

            for (Fireball fireball : map[x][y]) {
                pair<int, int> next = get_next_loc(x, y, fireball);
                new_map[next.first][next.second].push_back(fireball);
            }

        }
    }

    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            map[x][y] = new_map[x][y];
        }
    }
}

bool is_all_even_or_odd(vector<int> directions) {
    bool even = false;
    bool odd = false;
    for (int dir : directions) {
        if (dir % 2 == 0) even = true;
        else odd = true;
    }
    return (!even && odd) || (even && !odd);
}

void combine_fireballs() {
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            if (map[x][y].size() < 2) continue;

            int total_mass = 0, total_speed = 0;
            vector<int> directions;
            for (Fireball fireball : map[x][y]) {
                directions.push_back(fireball.direction);
                total_mass += fireball.mass;
                total_speed += fireball.speed;
            }

            int mass = total_mass / 5;
            int speed = total_speed / map[x][y].size();

            map[x][y].clear();

            if (mass == 0) continue;

            if (is_all_even_or_odd(directions)) {
                for (int d = 0; d < 8; d += 2) {
                    int nx = convert_loc(x + dx[d]);
                    int ny = convert_loc(y + dy[d]);
                    map[x][y].push_back({ mass, speed, d });
                }
            } else {
                for (int d = 1; d < 8; d += 2) {
                    int nx = convert_loc(x + dx[d]);
                    int ny = convert_loc(y + dy[d]);
                    map[x][y].push_back({ mass, speed, d });
                }
            }
        }
    }

}

int count_mass() {
    int ret = 0;
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            int sum = 0;
            for (Fireball fireball : map[x][y]) sum += fireball.mass;
            ret += sum;
        }
    }
    return ret;
}

void solution() {
    while (K--) {
        move_fireballs();
        combine_fireballs();
    }

    int answer = count_mass();
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}