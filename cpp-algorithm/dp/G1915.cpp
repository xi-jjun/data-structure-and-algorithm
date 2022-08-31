// https://yabmoons.tistory.com/158
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int N, M;
string line;
int map[1001][1001];

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    for (int i = 1; i <= N; i++) {
        cin >> line;
        for (int j = 0; j <= line.length(); j++) {
            map[i][j + 1] = line[j] - '0';
        }
    }
}

void solution() {
    int answer = 0;
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            if (map[x][y] == 0) continue;
            map[x][y] = min(map[x - 1][y - 1], min(map[x - 1][y], map[x][y - 1])) + 1;
            answer = max(answer, map[x][y]);
        }
    }

    cout << answer * answer;
}

int main() {
    input();
    solution();
    return 0;
}