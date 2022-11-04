#include <iostream>
#include <queue>
#include <cstring>
#include <tuple>

using namespace std;

typedef tuple<int, int, int> tiii;

const int dx[] = {-1, 0, 1, 0};
const int dy[] = {0, 1, 0, -1};
const int WALL = 0;
const int ROAD = 1;
const int DEST = 2;
const int MAX = 1061109567;

int N, M;
int map[1001][1001];
bool visited[1001][1001];
int distances[1001][1001];
pair<int, int> start;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> map[i][j];
            if (map[i][j] == DEST) start = {i, j};
        }
    }
}

struct cmp {
    bool operator()(tiii t1, tiii t2) {
        return get<2>(t1) > get<2>(t2);
    }
};

bool in_range(int x, int y) {
    return !(x < 0 || x >= N || y < 0 || y >= M);
}

int min(int a, int b) {return a > b ? b : a; }

void bfs(pair<int, int> start) {
    memset(distances, 0x3f, sizeof(distances));
    distances[start.first][start.second] = 0;
    memset(visited, false, sizeof(visited));
    visited[start.first][start.second] = true;

    // queue<tiii> q;
    // q.push({start.first, start.second, 0});
    priority_queue<tiii, vector<tiii>, cmp> q;
    q.push({start.first, start.second, 0});
    

    while (!q.empty()) {
        int x, y, distance;
        // tie(x, y, distance) = q.front(); q.pop();
        tie(x, y, distance) = q.top(); q.pop();
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (in_range(nx, ny) && !visited[nx][ny] && map[nx][ny] == ROAD) {
                visited[nx][ny] = true;
                distances[nx][ny] = min(distances[nx][ny], distance + 1);
                q.push({nx, ny, distance + 1});
            }
        }
    }
} 

void solution() {
    bfs(start);

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (map[i][j] == WALL && distances[i][j] == MAX) cout << "0 ";
            else if (map[i][j] == ROAD && distances[i][j] == MAX) cout << "-1 ";
            else cout << distances[i][j] << ' ';
        }
        cout << "\n";
    }
}

int main() {
    input();
    solution();
    return 0;
}