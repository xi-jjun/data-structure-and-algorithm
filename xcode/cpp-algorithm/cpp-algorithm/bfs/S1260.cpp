//
//  S1260.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  baekjoon
//  problem link : https://www.acmicpc.net/problem/1260
//  title : DFS와 BFS
//  type : BFS, DFS
//  difficulty : Silver 2
//
//  == solution info ==
//  과거의 기억을 더듬더듬... 더듬이...
//  노드의 숫자가 작은 것 부터 확인해야 하는게 key point.
//  
//
//  Created by 김재준 on 10/4/24.
//

#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

int N, M, V;
int graph[1001][1001];
int visited[1001];

void bfs(int root) {
    queue<int> q;
    q.push(root);
    visited[root] = 1;
    
    while (!q.empty()) {
        int now_node = q.front(); q.pop();
        cout << now_node << ' ';
        visited[now_node] = 1;
        for (int next_node = 1; next_node <= N; next_node++) {
            if (!visited[next_node] && graph[now_node][next_node]) {
                visited[next_node] = 1;
                q.push(next_node);
            }
        }
    }
}

void dfs(int node) {
    visited[node] = 1;
    cout << node << ' ';
    
    for (int next_node = 1; next_node <= N; next_node++) {
        if (!visited[next_node] && graph[node][next_node]) {
            dfs(next_node);
        }
    }
}

void get_input() {
    cin >> N >> M >> V;
    memset(graph, 0, sizeof(graph));
    memset(visited, 0, sizeof(visited));
    
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        graph[a][b] = 1;
        graph[b][a] = 1;
    }
}

int main() {
    get_input();
    dfs(V);
    memset(visited, 0, sizeof(visited));
    cout << "\n";
    bfs(V);
    return 0;
}
