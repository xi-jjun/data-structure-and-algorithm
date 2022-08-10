#include <iostream>

typedef long long ll;
using namespace std;

const int MOD = 10007;
int N, K;
ll c[1001][1001];

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> K;
}

void solution() {
	c[0][0] = c[1][0] = c[1][1] = 1;
	for (int a = 2; a <= N; a++) {
		for (int b = 0; b <= a; b++) {
			if (b == 0 || b == a) c[a][b] = 1;
			else c[a][b] = (c[a - 1][b - 1] + c[a - 1][b]) % MOD;
		}
	}

	cout << (c[N][K] % MOD);
}

int main() {
	get_input();
	solution();
	return 0;
}
