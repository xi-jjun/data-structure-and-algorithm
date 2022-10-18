#include <iostream>
#include <vector>
#include <cstring>
#include <tuple>
#include <queue>

#define MAX_ 1000000001

using namespace std;

int N, Q, answer;
int p, q, r, k, v;
vector<pair<int, int>> graph[5001];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> Q;
    for (int i = 0; i < N - 1; i++) {
        cin >> p >> q >> r;
        graph[p].push_back({r, q});
        graph[q].push_back({r, p});
    }
}

int min(int a, int b) { return a > b ? b : a; }

void bfs(int k, int v) {
    queue<int> q;
    q.push(v);

    bool visited[5001];
    memset(visited, false, sizeof(visited));
    visited[v] = true;

    while (!q.empty()) {
        int now_video = q.front(); q.pop();

        for (auto next : graph[now_video]) {
            int next_video = next.second;
            int next_usado = next.first;

            if (!visited[next_video] && next_usado >= k) {
                visited[next_video] = true;
                answer++;
                q.push(next_video);
            }
        }
    }
}

void solution() {
    for (int i = 0; i < Q; i++) {
        cin >> k >> v;
        answer = 0;
        bfs(k, v);
        cout << answer << "\n";
    }
}

int main() {
    input();
    solution();
    return 0;
}