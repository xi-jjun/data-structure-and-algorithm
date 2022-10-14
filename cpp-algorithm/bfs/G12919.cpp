#include <iostream>
#include <set>
#include <algorithm>
#include <string>
#include <queue>

using namespace std;

string S, T;
set<string> visited;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> S >> T;
}

bool is_visited(string str) {
    return visited.find(str) != visited.end();
}

int bfs(string start) {
    queue<string> q;
    q.push(start);
    visited.insert(start);

    while (!q.empty()) {
        string now = q.front(); q.pop();

        if (now[0] == 'B') {
            string next = now;
            reverse(next.begin(), next.end());
            next = next.substr(0, now.length() - 1);
            if (next == S) return 1;
            q.push(next);
        }

        if (now.length() != 0 && now[now.length() - 1] == 'A') {
            string next = now.substr(0, now.length() - 1);
            if (next == S) return 1;
            q.push(next);
        }
    }

    return 0;
}

void solution() {
    int answer = bfs(T);
    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}