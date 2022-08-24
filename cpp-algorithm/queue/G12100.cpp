#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>

#define MAX_ 21
#define EMPTY 0;
#define UP 0
#define RIGHT 1
#define DOWN 2
#define LEFT 3

using namespace std;

struct Board {
    vector<vector<int>> map;
    int max_block;
    int count;
};

int N, answer = 0;
vector<vector<int>> init_map(MAX_, vector<int> (MAX_, 0));

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> init_map[i][j];
        }
    }
}

int find_max_block(vector<vector<int>> map) {
    int ret = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            ret = max(ret, map[i][j]);
        }
    }
    return ret;
}

vector<queue<int>> q_up_reset(vector<vector<int>> ex_map) {
    vector<queue<int>> q(MAX_);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            q[i].push(ex_map[j][i]);
        }
    }
    return q;
}

vector<queue<int>> q_down_reset(vector<vector<int>> ex_map) {
    vector<queue<int>> q(MAX_);
    for (int i = 0; i < N; i++) {
        for (int j = N - 1; j >= 0; j--) {
            q[i].push(ex_map[j][i]);
        }
    }
    return q;
}

vector<queue<int>> q_left_reset(vector<vector<int>> ex_map) {
    vector<queue<int>> q(MAX_);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            q[i].push(ex_map[i][j]);
        }
    }
    return q;
}

vector<queue<int>> q_right_reset(vector<vector<int>> ex_map) {
    vector<queue<int>> q(MAX_);
    for (int i = 0; i < N; i++) {
        for (int j = N - 1; j >= 0; j--) {
            q[i].push(ex_map[i][j]);
        }
    }
    return q;
}

vector<vector<int>> push_left(vector<vector<int>> ex_map) {
    vector<vector<int>> ret(MAX_, vector<int> (MAX_, 0));
    vector<queue<int>> q = q_left_reset(ex_map);

    for (int row = 0; row < N; row++) {
        int col = 0;
        bool is_added = false;

        while (!q[row].empty()) {
            int data = q[row].front();
            q[row].pop();

            if (data == 0) continue;
            else if (is_added) { // 현재 col번째 숫자는 이미 합쳐진 상태. 따라서 다음번에 0이 아니라면 뭐가 오든간에 다음에 추가되어야 함
                ret[row][++col] = data;
                is_added = false;
            } else if (ret[row][col] == data) { // 현재 col번째 숫자가 추가될 숫자(data)와 같아서 합쳐질 수 있을 때
                ret[row][col] = data * 2; // 현재 col에 합치고
                is_added = true; // 합쳐서 더이상 못 합친다는 표시
            } else if (ret[row][col] == 0) ret[row][col] = data; // 현재 col번째에 숫자가 추가 안된 경우(맨 처음)
            else { // 현재 col 번째 숫자(0아닌)와 추가될 숫자(data)가 달라서 다음번째에 추가해야 하는 경우
                ret[row][++col] = data;
            }
        }

        // 위 과정에서 다 안채워졌다면, 그 다음 모든 숫자들은 0으로 빈칸 표시
        if (col != N - 1) {
            while (col++ != N - 1) ret[row][col] = EMPTY;
        }
    }

    return ret;
}

vector<vector<int>> push_right(vector<vector<int>> ex_map) {
    vector<vector<int>> ret(MAX_, vector<int> (MAX_, 0));
    vector<queue<int>> q = q_right_reset(ex_map);

    for (int row = 0; row < N; row++) {
        int col = N - 1;
        bool is_added = false;

        while (!q[row].empty()) {
            int data = q[row].front();
            q[row].pop();

            if (data == 0) continue;
            else if (is_added) { // 현재 col번째 숫자는 이미 합쳐진 상태. 따라서 다음번에 0이 아니라면 뭐가 오든간에 다음에 추가되어야 함
                ret[row][--col] = data;
                is_added = false;
            } else if (ret[row][col] == data) { // 현재 col번째 숫자가 추가될 숫자(data)와 같아서 합쳐질 수 있을 때
                ret[row][col] = data * 2; // 현재 col에 합치고
                is_added = true; // 합쳐서 더이상 못 합친다는 표시
            } else if (ret[row][col] == 0) ret[row][col] = data; // 현재 col번째에 숫자가 추가 안된 경우(맨 처음)
            else { // 현재 col 번째 숫자(0아닌)와 추가될 숫자(data)가 달라서 다음번째에 추가해야 하는 경우
                ret[row][--col] = data;
            }
        }

        // 위 과정에서 다 안채워졌다면, 그 다음 모든 숫자들은 0으로 빈칸 표시
        if (col != 0) {
            while (col-- != 0) ret[row][col] = EMPTY;
        }
    }

    return ret;
}

vector<vector<int>> push_down(vector<vector<int>> ex_map) {
    vector<vector<int>> ret(MAX_, vector<int> (MAX_, 0));
    vector<queue<int>> q = q_down_reset(ex_map);

    for (int col = 0; col < N; col++) {
        int row = N - 1;
        bool is_added = false;

        while (!q[col].empty()) {
            int data = q[col].front();
            q[col].pop();

            if (data == 0) continue;
            else if (is_added) { // 현재 row번째 숫자는 이미 합쳐진 상태. 따라서 다음번에 0이 아니라면 뭐가 오든간에 다음에 추가되어야 함
                ret[--row][col] = data;
                is_added = false;
            } else if (ret[row][col] == data) { // 현재 row번째 숫자가 추가될 숫자(data)와 같아서 합쳐질 수 있을 때
                ret[row][col] = data * 2; // 현재 row에 합치고
                is_added = true; // 합쳐서 더이상 못 합친다는 표시
            } else if (ret[row][col] == 0) ret[row][col] = data; // 현재 row번째에 숫자가 추가 안된 경우(맨 처음)
            else { // 현재 row 번째 숫자(0아닌)와 추가될 숫자(data)가 달라서 다음번째에 추가해야 하는 경우
                ret[--row][col] = data;
            }
        }

        // 위 과정에서 다 안채워졌다면, 그 다음 모든 숫자들은 0으로 빈칸 표시
        if (row != 0) {
            while (row-- != 0) ret[row][col] = EMPTY;
        }
    }

    return ret;
}

vector<vector<int>> push_up(vector<vector<int>> ex_map) {
    vector<vector<int>> ret(MAX_, vector<int> (MAX_, 0));
    vector<queue<int>> q = q_up_reset(ex_map);

    for (int col = 0; col < N; col++) {
        int row = 0;
        bool is_added = false;

        while (!q[col].empty()) {
            int data = q[col].front();
            q[col].pop();

            if (data == 0) continue;
            else if (is_added) { // 현재 row번째 숫자는 이미 합쳐진 상태. 따라서 다음번에 0이 아니라면 뭐가 오든간에 다음에 추가되어야 함
                ret[++row][col] = data;
                is_added = false;
            } else if (ret[row][col] == data) { // 현재 row번째 숫자가 추가될 숫자(data)와 같아서 합쳐질 수 있을 때
                ret[row][col] = data * 2; // 현재 row에 합치고
                is_added = true; // 합쳐서 더이상 못 합친다는 표시
            } else if (ret[row][col] == 0) ret[row][col] = data; // 현재 row번째에 숫자가 추가 안된 경우(맨 처음)
            else { // 현재 row 번째 숫자(0아닌)와 추가될 숫자(data)가 달라서 다음번째에 추가해야 하는 경우
                ret[++row][col] = data;
            }
        }

        // 위 과정에서 다 안채워졌다면, 그 다음 모든 숫자들은 0으로 빈칸 표시
        if (row != N - 1) {
            while (row++ != N - 1) ret[row][col] = EMPTY;
        }
    }

    return ret;
}

Board push_direction(vector<vector<int>> ex_map, int direction) {
    Board ret;
    switch (direction) {
        case UP:
            ret.map = push_up(ex_map);
            break;
        case DOWN:
            ret.map = push_down(ex_map);
            break;
        case LEFT:
            ret.map = push_left(ex_map);
            break;
        case RIGHT:
            ret.map = push_right(ex_map);
            break;
    }
    ret.max_block = find_max_block(ret.map);
    return ret;
}

void solution() {
    queue<Board> q;
    Board first;
    first.map = init_map;
    first.count = 0;
    first.max_block = find_max_block(init_map);
    q.push(first);

    while (!q.empty()) {
        Board now = q.front();
        q.pop();

        if (now.count > 5) continue;
        
        answer = max(answer, now.max_block);
        for (int dir = 0; dir < 4; dir++) {
            Board next = push_direction(now.map, dir);
            next.count = now.count + 1;
            q.push(next);
        }
    }
}

int main() {
    input();
    solution();
    cout << answer;
    return 0;
}