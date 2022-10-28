#include <iostream>

using namespace std;

int N;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);;
    cin >> N;
}

void solution() {
    int cnt = 0;
    while (1) {
        if (N % 5 != 0) {
            if (N < 3) break;
            N -= 3;
        } else {
            break;
        }
        cnt++;
    }

    if (N % 3 != 0 && N % 5 != 0) cout << "-1";
    else cout << (cnt + (N / 5));
}

int main() {
    input();
    solution();
    return 0;
}