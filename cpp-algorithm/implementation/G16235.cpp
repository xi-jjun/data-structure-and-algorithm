#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
const int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};

int N, M, K;
int A[11][11];
vector<int> grounds[11][11]; // tree가 저장될 땅
int nutritions[11][11]; // 영양 저장

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M >> K;
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= N; y++) {
            cin >> A[x][y];
            nutritions[x][y] = 5;
        }
    }

    int x, y, z;
    for (int i = 0; i < M; i++) {
        cin >> x >> y >> z;
        grounds[x][y].push_back(z);
    }
}

bool in_range(int x, int y) {
    return !(x < 1 || x > N || y < 1 || y > N);
}

void spring_and_summer() {
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= N; y++) {
            sort(grounds[x][y].begin(), grounds[x][y].end());
            vector<int> trees;

            int dead_start = -1;
            for (int i = 0; i < grounds[x][y].size(); i++) {
                int tree = grounds[x][y][i];
                if (nutritions[x][y] >= tree) {
                    nutritions[x][y] -= tree;
                    trees.push_back(tree + 1);
                } else {
                    dead_start = i;
                    break;
                }
            }

            for (int i = dead_start; i < grounds[x][y].size(); i++) {
                nutritions[x][y] += grounds[x][y][i] / 2;
            }
            grounds[x][y] = trees;
        }
    }
}

void fall() {
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= N; y++) {
            for (int i = 0; i < grounds[x][y].size(); i++) {
                if (grounds[x][y][i] % 5 == 0) {
                    for (int d = 0; d < 8; d++) {
                        if (in_range(x + dx[d], y + dy[d])) {
                            grounds[x + dx[d]][y + dy[d]].push_back(1);
                        }
                    }
                }
            }
        }
    }
}

void winter() {
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= N; y++) {
            nutritions[x][y] += A[x][y];
        }
    }
}

int count_alive_trees() {
    int ret = 0;
    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= N; y++) {
            ret += grounds[x][y].size();
        }
    }

    return ret;
}

void solution() {
    for (int year = 1; year <= K; year++) {
        spring_and_summer();
        fall();
        winter();
    }

    int answer = count_alive_trees();
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}