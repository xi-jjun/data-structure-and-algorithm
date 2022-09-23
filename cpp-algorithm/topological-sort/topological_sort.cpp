#include <vector>
#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

int in_degree[10]; // MAX=10
vector<vector<int>> graph(10);

void make_graph() {
    memset(in_degree, 0, sizeof(in_degree));
    graph[1].push_back(2);
    in_degree[2]++;

    graph[1].push_back(5);
    in_degree[5]++;

    graph[2].push_back(3);
    in_degree[3]++;

    graph[3].push_back(4);
    in_degree[4]++;

    graph[5].push_back(6);
    in_degree[6]++;

    graph[4].push_back(6);
    in_degree[6]++;

    graph[6].push_back(7);
    in_degree[7]++;
}

void topological_sort() {
    queue<int> q;

    // 1단계 : indegree==0이면 Q에 삽입
    for (int node = 1; node <= 7; node++) {
        if (in_degree[node] == 0) q.push(node);
    }

    vector<int> answer_arr;
    // 2단계 : Q에서 원소를 꺼내어 연결된 모든 간선 제거
    while (!q.empty()) {
        int zero_indegree = q.front(); // Q에서 원소 꺼내기
        answer_arr.push_back(zero_indegree);
        q.pop();

        // 연결된 모든 간선 제거
        // graph[zero_indegree] : 선행조건이 zero_indegree 노드인 노드들의 배열 => 따라서 이 배열에 해당하는 애들의 차수를 1씩 줄여야 함
        for (auto node : graph[zero_indegree]) {
            in_degree[node]--;
            if (in_degree[node] == 0) q.push(node); // node의 차수가 0이 되면 다시 Q에 삽입
        }
    }

    /**
     *  ===topological sort results===
        1 → 2 → 5 → 3 → 4 → 6 → 7
     */
    cout << "\n===topological sort results===\n";
    for (int ans : answer_arr) cout << ans << " → ";
    cout << "\n";
}

int main() {
    make_graph();
    topological_sort();
    return 0;
}