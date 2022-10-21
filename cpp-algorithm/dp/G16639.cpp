#include <iostream>
#include <string>
#include <algorithm>
#include <climits>

using namespace std;

int N;
string expression;
int max_dp[40][40], min_dp[40][40];

int max(int a, int b) { return a > b ? a : b; }
int min(int a, int b) { return a > b ? b : a; }

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> expression;
}

void init() {
    for (int i = 0; i < 40; i++) {
        for (int j = 0; j < 40; j++) {
            max_dp[i][j] = INT_MIN;
            min_dp[i][j] = INT_MAX;
        }
    }
}

bool is_numeric(char c) {
    return '0' <= c && c <= '9';
}

int calculate(int a, char op, int b) {
    switch (op) {
        case '+': return a + b;
        case '-': return a - b;
        case '*': return a * b;
        default: return -1;
    }
}

void solution() {
    init();
    for (int i = 0; i < N; i++) {
        if (is_numeric(expression[i])) {
            max_dp[i][i] = expression[i] - '0';
            min_dp[i][i] = expression[i] - '0';
        }
    }

    // 총 cnt개의 피연산자들을 나눠서 계산할 것이다.
    for (int cnt = 2; cnt < N; cnt += 2) {

        // i ~ i+j-2,  i+j ~ i+cnt
        for (int i = 0; i < N - cnt; i += 2) {
            for (int j = 2; j <= cnt; j += 2) {
                // cout << "cnt, i, j ==> " << cnt << ' ' << i << ' ' << j << "\n";
                // i ~ i + j - 2 : i+j-2 는 다음 계산할 수 의 바로 앞까지 계산한 결과
                // 그 다음인 i + j로 다음 계산할 숫자 시작.
                // i + j ~ i + cnt : i+cnt => 현재 cnt개만 계산할 것이기에
                int results[4] = {
                    calculate(min_dp[i][i + j - 2], expression[i + j - 1], min_dp[i + j][i + cnt]),
                    calculate(max_dp[i][i + j - 2], expression[i + j - 1], min_dp[i + j][i + cnt]),
                    calculate(min_dp[i][i + j - 2], expression[i + j - 1], max_dp[i + j][i + cnt]),
                    calculate(max_dp[i][i + j - 2], expression[i + j - 1], max_dp[i + j][i + cnt])
                };

                sort(results, results + 4);
                min_dp[i][i + cnt] = min(min_dp[i][i + cnt], results[0]);
                max_dp[i][i + cnt] = max(max_dp[i][i + cnt], results[3]);
            }
        }
    }

    cout << max_dp[0][N - 1];
}

int main() {
    input();
    solution();
    return 0;
}