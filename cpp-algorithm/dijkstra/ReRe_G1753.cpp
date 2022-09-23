#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

#define MAX_ 20001
#define INF 1000000000

using namespace std;

int V, E, start;
int dp[MAX_];
vector<pair<int, int>> graph[MAX_];

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> V >> E >> start;

    int u, v, cost;
    for (int i = 0; i < E; i++) {
        cin >> u >> v >> cost;
        graph[u].push_back({v, cost});
    }
}

void dijkstra() {
    bool visited[MAX_] = {false};
    fill(visited, visited + V + 1, false);
    fill(dp, dp + V + 1, INF);

    dp[start] = 0;
    priority_queue<pair<int, int>> pq;
    pq.push({0, start});

    while (!pq.empty()) {
        pair<int, int> n = pq.top(); pq.pop();
        int cost = -n.first;
        int now = n.second;

        if (visited[now]) continue;

        visited[now] = true;
        for (auto next_ : graph[now]) {
            int next = next_.first;
            int next_cost = next_.second;

            if (dp[next] > dp[now] + next_cost) {
                dp[next] = dp[now] + next_cost;
                pq.push({-dp[next], next});
            }
        }
    }
}

void print_ans() {
    for (int i = 1; i <= V; i++) {
        if (dp[i] == INF) cout << "INF\n";
        else cout << dp[i] << "\n";
    }
}

int main() {
    input();
    dijkstra();
    print_ans();

    return 0;
}