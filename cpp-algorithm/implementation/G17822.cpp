// 2시간 30분... 방향 1개 빼먹은거 + 회전 고려 안함 + 평균 변환 못함 + 평균을 곱해서 int로 바꿀꺼면 10000은 곱해줘야 함.
#include <iostream>
#include <cstring>
#include <tuple>
#include <queue>
#include <deque>

using namespace std;

const int ERASED = 0;
const int dx[] = {0, -1, 0, 1};
const int dy[] = {1, 0, -1, 0}; 

int N, M, T;
deque<int> place[51];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M >> T;
    for (int i = 1; i <= N; i++) {
        int number;

        for (int j = 0; j < M; j++) {
            cin >> number;
            place[i].push_back(number);
        }
    }
}

void rotate_clockwise(int place_num, int times) {
    while (times--) {
        int last = place[place_num][M - 1];
        place[place_num].pop_back();
        place[place_num].push_front(last);
    }   
}

void rotate_counter_clockwise(int place_num, int times) {
    while (times--) {
        int first = place[place_num][0];
        place[place_num].pop_front();
        place[place_num].push_back(first);
    }
}

void rotate_place(int place_num, int dir, int times) {
    if (dir == 0) {
        rotate_clockwise(place_num, times);
    } else {
        rotate_counter_clockwise(place_num, times);
    }
}

bool in_range(int x, int y) {
    return !(x < 1 || x > N || y < 0 || y >= M);
}

bool is_erasable(int x, int y) {
    for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (y == 0 && d == 2) ny = M - 1;
        else if (y == M - 1 && d == 0) ny = 0;

        if (in_range(nx, ny) && place[x][y] == place[nx][ny]) {
            return true;
        }
    }
    return false;
}

void erase(int x, int y) {
    int NUMBER = place[x][y];
    place[x][y] = ERASED;

    queue<pair<int, int>> q;
    q.push({x, y});

    while (!q.empty()) {
        tie(x, y) = q.front(); q.pop();

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (y == 0 && d == 2) ny = M - 1;
            else if (y == M - 1 && d == 0) ny = 0;

            if (in_range(nx, ny) && place[nx][ny] == NUMBER) {
                place[nx][ny] = ERASED;
                q.push({nx, ny});
            } 
        }
    }
}

bool erase_adjacent_element() {
    bool erased = false;
    
    for (int i = 1; i <= N; i++) {
        for (int j = 0; j < M; j++) {
            if (is_erasable(i, j) && place[i][j] != ERASED) {
                erased = true;
                erase(i, j);
            }
        }
    }

    return erased;
}

pair<int, int> sum_total_elements() {
    int dividor = 0;
    int ret = 0;
    for (int place_num = 1; place_num <= N; place_num++) {
        for (auto number : place[place_num]) {
            ret += number;
            dividor = number != 0 ? dividor + 1 : dividor;
        }
    }
    return {ret, dividor};
}

double avg_of_place_numbers() {
    int dividor = 0;
    int total_sum = 0;
    tie(total_sum, dividor) = sum_total_elements();

    return (double)total_sum / dividor;
}

void change_place_numbers(int place_num, int avg) {
    for (int i = 0; i < M; i++) {
        int number = place[place_num][i] * 10000;

        if (number != 0) {
            if (number > avg) place[place_num][i]--;
            else if (number < avg) place[place_num][i]++;
        }
    }
}

void change_places_numbers() {
    int avg = avg_of_place_numbers() * 10000;

    for (int p = 1; p <= N; p++) {
        change_place_numbers(p, avg);
    }
}

void rotate_places(int x, int dir, int k) {
    for (int p = 1; p <= N; p++) {
        if (p % x == 0) {
            rotate_place(p, dir, k);
        }
    }
}

void solution() {
    int x, dir, k;

    while (T--) {
        cin >> x >> dir >> k;
        rotate_places(x, dir, k);
        bool erased = erase_adjacent_element();
        if (!erased) {
            change_places_numbers();
        }
    }

    int answer = sum_total_elements().first;
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}