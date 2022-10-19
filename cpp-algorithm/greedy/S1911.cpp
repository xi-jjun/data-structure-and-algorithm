// 30분 이전에 풀어봤던 그리디와 유형이 같아서 쉽게 떠올려서 풀었다. 검증에 대해서 생각을 많이 해본 문제
#include <iostream>
#include <tuple>
#include <vector>
#include <algorithm>

using namespace std;

int N, L;
vector<pair<int, int>> puddles;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int start, end;
    cin >> N >> L;
    while (N--) {
        cin >> start >> end;
        puddles.push_back({start, end});
    }
}

void solution() {
    sort(puddles.begin(), puddles.end());

    int next_bridge_location = -1;
    int answer = 0;
    int start, end;

    for (auto puddle : puddles) {
        tie(start, end) = puddle;

        if (end < next_bridge_location) continue;

        start = max(start, next_bridge_location);
        int needed_length = end - start;
        int needed_cnt = needed_length %  L == 0 ? needed_length / L : needed_length / L + 1;

        answer += needed_cnt;
        next_bridge_location = start + needed_cnt * L;
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}