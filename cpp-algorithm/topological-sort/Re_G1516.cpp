#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>

using namespace std;

int N;
int dp[501];
int in_degree[501];
int build_time[501];
vector<vector<int>> graph(501);

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N;

    memset(in_degree, 0, sizeof(in_degree));
    int time, building;
    // building -> node : node를 짓기 위해 선행되어야 할 건물은 building이다.
    for (int node = 1; node <= N; node++) {
        cin >> build_time[node] >> building;
        while (building != -1) {
            graph[building].push_back(node); // building->node 조건.
            in_degree[node]++; // node에 indegree가 1추가된다.
            cin >> building;
        }
    }
}

void solution() {
    queue<int> buildings;
    for (int node = 1; node <= N; node++) {
        if (in_degree[node] == 0) buildings.push(node);
    }

    // dp를 건물 짓는 시간으로 초기화
    for (int node = 1; node <= N; node++) dp[node] = build_time[node];

    while (!buildings.empty()) {
        int building = buildings.front(); buildings.pop();

        for (int next : graph[building]) {
            in_degree[next]--;
            if (in_degree[next] == 0) buildings.push(next);
            // next의 시간 중, 긴 시간에 의해 건물 짓는 시간이 결정되기 때문에 max사용한 것이다.
            dp[next] = max(dp[next], dp[building] + build_time[next]);
        }
    }

    for (int i = 1; i <= N; i++) cout << dp[i] << "\n";
}

int main() {
    input();
    solution();
    return 0;
}