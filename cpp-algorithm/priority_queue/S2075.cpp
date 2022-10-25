#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int N;
priority_queue<int, vector<int>, greater<int>> pq;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            int number;
            cin >> number;
            pq.push(number);
            if (pq.size() > N) pq.pop();
        }
    }
}

void solution() {
    while (pq.size() != 5) {
        pq.pop();
    }
    cout << pq.top();
}

int main() {
    input();
    solution();
    return 0;
}