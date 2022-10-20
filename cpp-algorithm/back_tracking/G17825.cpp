#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

const int MAX_TURN = 10;
const int RED = 0;
const int BLUE = 1;
const int START = 1;
const int END = 33;
const int INVALID = -10;
const int blue_loc[3] = {6, 11, 16}; // blue locations number
const int scores[34] = { // scores[loc] = score
    0, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40,
    13, 16, 19, 25, 22, 24, 26, 27, 28, 30, 35, 0
};
const vector<int> map[34] = { // map[now loc] = next loc list
    {-1},
    {2}, {3}, {4}, {5}, {6},  // ~ [5]
    {7, 22}, {8}, {9}, {10}, {11}, {12, 26}, // ~ [11]
    {13}, {14}, {15}, {16}, {17, 30}, // ~ [16]
    {18}, {19}, {20}, {21}, {END}, // ~ [21] 21에 가면 그 다음이 33(END)
    {23}, {24}, {25}, {31}, // ~ [25]
    {27}, {25}, {25}, {28}, {29}, // ~ [30]
    {32}, {21}, {-1} // ~ [33] 33=END
};

int answer = 0;
int selected_horses[11]; // [turn] : horse number
bool selected[11][5]; // [turn][horse number] = turn번째에 horse number 말이 선택됐는지
int horses_loc[5]; // [horse number] = location number
int moves[11]; // [turn] = move_cnt

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    for (int m = 1; m <= 10; m++) {
        cin >> moves[m];
    }
}

void init_horses() {
    for (int h = 1; h <= 4; h++) {
        horses_loc[h] = START;
    }
}

bool is_blue(int location) {
    return location == blue_loc[0] || location == blue_loc[1] || location == blue_loc[2];
}

int max(int a, int b) { return a > b ? a : b; }

bool invalid_location(int location, int horse) {
    for (int h = 1; h <= 4; h++) {
        if (h != horse && horses_loc[h] == location) return true;
    }
    return false;
}

int move_horse(int horse, int cnt) {
    if (horses_loc[horse] == END) return 0;

    int now_loc = horses_loc[horse];
    int next_loc = is_blue(now_loc) ? map[now_loc][BLUE] : map[now_loc][RED];
    if (next_loc == END) {
        horses_loc[horse] = END;
        return 0;
    }
    
    --cnt;

    while (cnt--) {
        next_loc = map[next_loc][RED];

        if (next_loc == END) {
            horses_loc[horse] = END;
            return 0;
        }
    }

    horses_loc[horse] = next_loc;

    return invalid_location(next_loc, horse) ? INVALID : scores[next_loc];
}

int game_start() {
    init_horses();
    int score = 0;

    for (int turn = 1; turn <= MAX_TURN; turn++) {
        int now_horse = selected_horses[turn]; // 이번에 이동할 말 번호
        int moved_cnt = moves[turn]; // 이동할 거리

        int now_score = move_horse(now_horse, moved_cnt);
        if (now_score == INVALID) return -1; // 도착하려는 칸에 누군가 있었기 때문에 이번 게임은 무효

        score += now_score;
    }

    return score;
}

void select_horses(int depth) {
    if (depth == MAX_TURN) {
        int score = game_start();
        // int score = 0;
        answer = max(answer, score);
        return;
    } 

    for (int horse_num = 1; horse_num <= 4; horse_num++) {
        if (!selected[depth + 1][horse_num]) {
            selected[depth + 1][horse_num] = true;
            selected_horses[depth + 1] = horse_num;
            select_horses(depth + 1);
            selected[depth + 1][horse_num] = false;
        }
    }
}

void solution() {
    memset(selected, false, sizeof(selected));
    select_horses(0);
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}