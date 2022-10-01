#include <iostream>
#include <string>

using namespace std;

string line;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> line;
}

void solution() {
    int total_len = line.length();
    int idx = 0;
    while (idx + 10 < total_len) {
        cout << line.substr(idx, 10) << "\n";
        idx += 10;
    }
    cout << line.substr(idx) << "\n";
}

int main() {
    input();
    solution();

    return 0;
}