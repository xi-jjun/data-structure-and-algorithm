// programmers Level2 게임 맵 최단거리 
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

const int dx[4] = {-1, 0, 1, 0};
const int dy[4] = {0, 1, 0, -1};

int N, M;
bool visited[101][101];

void reset(vector<vector<int> > maps) {
    N = maps.size();
    M = maps[0].size();
    memset(visited, false, sizeof(visited));
}

bool is_end(int x, int y) {
    return x == N - 1 && y == M - 1;
}

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= M);
}

int solution(vector<vector<int> > maps) {
    reset(maps);
    
    priority_queue<pair<int, pair<int, int> > > pq;
    visited[0][0] = true;
    pq.push({-1, {0, 0}});
    
    while (!pq.empty()) {
        int x = pq.top().second.first;
        int y = pq.top().second.second;
        int count = -pq.top().first;
        pq.pop();
        
        if (is_end(x, y)) return count;
        
        for (int d = 0; d < 4; d++) {
            int next_x = x + dx[d];
            int next_y = y + dy[d];
            int next_cnt = count + 1;
            
            if (in_range(next_x, next_y) && !visited[next_x][next_y] && maps[next_x][next_y] == 1) {
                visited[next_x][next_y] = true;
                pq.push({-next_cnt, {next_x, next_y}});
            }
        }
    }
    
    return -1;
}
