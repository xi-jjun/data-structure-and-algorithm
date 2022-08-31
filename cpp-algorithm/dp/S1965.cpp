#include <iostream>
#include <algorithm>

using namespace std;

int N;
int boxes[1001], dp[1001];

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for (int i = 0; i < N; i++) cin >> boxes[i];
}

void solution() {
    dp[0] = 1;
    int answer = 1;
    for (int box = 1; box < N; box++) {
        dp[box] = 1;
        int std_box = boxes[box];
        for (int now = 0; now < box; now++) {
            if (std_box > boxes[now]) {
                dp[box] = max(dp[box], dp[now] + 1);
                answer = max(answer, dp[box]);
            }
        }
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}