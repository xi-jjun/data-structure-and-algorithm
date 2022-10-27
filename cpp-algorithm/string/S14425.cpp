#include <iostream>
#include <string>
#include <set>

using namespace std;

int N, M;
string str;
set<string> S;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    cin >> N >> M;
    while (N--) {
        cin >> str;
        S.insert(str);
    }
}

void solution() {
    int answer = 0;
    while (M--) {
        cin >> str;
        if (S.find(str) != S.end()) {
            answer++;
        }
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}