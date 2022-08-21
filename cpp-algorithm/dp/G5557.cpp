// https://velog.io/@lacram/C-%EB%B0%B1%EC%A4%80-5557%EB%B2%88-1%ED%95%99%EB%85%84
#include <iostream>
#include <cstring>

using namespace std;
typedef long long ll;

int N;
int num[101];
ll dp[101][21];

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> num[i];
	}
}

void solution() {
	memset(dp, 0, sizeof(dp));
	dp[1][num[1]] = 1;
	for (int times = 2; times <= N - 1; times++) {
		for (int result = 0; result <= 20; result++) {
			if (dp[times - 1][result] == 0) continue;

			if (result + num[times] <= 20) dp[times][result + num[times]] += dp[times - 1][result];
			if (result - num[times] >= 0) dp[times][result - num[times]] += dp[times - 1][result];
		}
	}

	cout << dp[N - 1][num[N]];
}

int main() {
	input();
	solution();
	return 0;
}
