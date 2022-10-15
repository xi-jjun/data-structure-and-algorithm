#include <iostream>
#include <vector>
#include <cstring>
#include <map>
#include <algorithm>

using namespace std;

int r, c, k;
int N = 3, M = 3;
int A[101][101];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> r >> c >> k;
    r--; c--;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            cin >> A[i][j];
        }
    }
}

int max(int a, int b) { return a > b ? a : b; }

bool is_end() {
    return A[r][c] == k;
}

// first : number
// second : count
bool cmp(pair<int, int> p1, pair<int, int> p2) {
    if (p1.second == p2.second) return p1.first < p2.first;
    return p1.second < p2.second;
}

vector<pair<int, int>> sort_counter(map<int, int> counter) {
    vector<pair<int, int>> ret;

    auto iter = counter.begin();
    while (iter != counter.end()) {
        ret.push_back({iter->first, iter->second});
        ++iter;
    }

    sort(ret.begin(), ret.end(), cmp);

    return ret;
}

void reset_col(int col) {
    for (int row = 0; row < 101; row++) {
        A[row][col] = 0;
    }
}

void operate_C() {
    int row_max = 0;
    int col_max = 0;

    for (int col = 0; col < M; col++) {
        map<int, int> counter; // [number] = count

        for (int row = 0; row < N; row++) {
            if (A[row][col] == 0) continue;;

            int number = A[row][col];
            counter[number]++;
        }

        vector<pair<int, int>> result = sort_counter(counter);
        reset_col(col);

        for (int r = 0; r < result.size(); r++) {
            if (2 * r + 1 > 100) break;
            A[2 * r][col] = result[r].first;
            A[2 * r + 1][col] = result[r].second;
        }

        col_max = max(col_max, result.size() * 2);
    }

    N = col_max;
}

void operate_R() {
    int row_max = 0;

    for (int i = 0; i < N; i++) {
        map<int, int> counter;

        for (int j = 0; j < M; j++) {
            if (A[i][j] == 0) continue;

            int number = A[i][j];
            counter[number]++;
        }

        vector<pair<int, int>> result = sort_counter(counter);

        memset(A[i], 0, sizeof(A[i]));

        for (int col = 0; col < result.size(); col++) {
            if (2 * col + 1 > 100) break;
            A[i][2 * col] = result[col].first;
            A[i][2 * col + 1] = result[col].second;
        }
        
        row_max = max(row_max, result.size() * 2);
    }

    M = row_max;
}

void solution() {
    int time = 0;
    while (!is_end()) {
        // R operation
        if (N >= M) {
            operate_R();
        } else { 
            operate_C();
        }

        if (++time > 100) break;
    }

    if (time > 100) cout << "-1";
    else cout << time;
}

int main() {
    input();
    solution();
    return 0;
}