#include <vector>
#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

int N, M;
int in_degree[32001];
vector<vector<int>> graph(32001);

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N >> M;

    int A, B;
    memset(in_degree, 0, sizeof(in_degree));
    for (int i = 0; i < M; i++) {
        cin >> A >> B;
        graph[A].push_back(B);
        in_degree[B]++;
    }
}

void print_ans(vector<int> ans) {
    for (int student : ans) cout << student << ' ';
}

void solution() {
    queue<int> students;
    for (int student = 1; student <= N; student++) {
        if (in_degree[student] == 0) students.push(student);
    }

    vector<int> answer;
    while (!students.empty()) {
        int student = students.front();
        answer.push_back(student);
        students.pop();

        for (int next : graph[student]) {
            if (--in_degree[next] == 0) students.push(next);
        }
    }

    print_ans(answer);
}

int main() {
    input();
    solution();

    return 0;
}