// 코딩테스트 연습 2020 카카오 인턴십 - 경주로 건설
// reference : https://ansohxxn.github.io/programmers/117/
#include <string>
#include <vector>
#include <cstring>
#include <queue>
#include <cmath>
#include <algorithm>

#define pipipii pair<int, pair<int, pair<int, int>>>
#define ROAD 0
#define INF 2e9

using namespace std;

int N;
int checked[30][30][4];

const int dx[4] = {0, 0, 1, -1};
const int dy[4] = {1, -1, 0, 0};

bool is_end(int x, int y) {
    return x == N - 1 && y == N - 1;
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= N);
}

int bfs(int start_x, int start_y, vector<vector<int>> board) {
    memset(checked, 0x3f, sizeof(checked));

    queue<pipipii> pq;
    pq.push({0, {-1, {start_x, start_y}}});
    checked[0][0][0] = 0;
    checked[0][0][1] = 0;
    checked[0][0][2] = 0;
    checked[0][0][3] = 0;
    
    int ret = INF;
    while (!pq.empty()) {
        pipipii now = pq.front();
        pq.pop();
        
        int now_cost = now.first;
        int now_dir = now.second.first;
        int now_x = now.second.second.first;
        int now_y = now.second.second.second;
        
        if (is_end(now_x, now_y)) {
            ret = min(ret, checked[now_x][now_y][now_dir]);
            continue;
        }
        
        for (int d = 0; d < 4; d++) {
            int next_x = now_x + dx[d];
            int next_y = now_y + dy[d];
            
            if (in_range(next_x, next_y) && board[next_x][next_y] == ROAD) {
                int next_cost = 0;
                if (now_dir == d || now_dir == -1) next_cost = now_cost + 100;
                else next_cost = now_cost + 600;
                
                if (checked[next_x][next_y][d] >= next_cost) {
                    checked[next_x][next_y][d] = next_cost;
                    pq.push({next_cost, {d, {next_x, next_y}}});
                }   
            }
        }
    }
    
    return ret;
}

int solution(vector<vector<int>> board) {
    N = board.size();
    int answer = bfs(0, 0, board);
    return answer;
}