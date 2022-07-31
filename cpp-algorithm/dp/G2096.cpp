#include <iostream>
#include <algorithm>
using namespace std;

int maxDp[2][4];
int minDp[2][4];
int max_ans, min_ans;

void solution(int N) {
	cin >> maxDp[0][1] >> maxDp[0][2] >> maxDp[0][3];
	minDp[0][1] = maxDp[0][1];
	minDp[0][2] = maxDp[0][2];
	minDp[0][3] = maxDp[0][3];

	for (int i = 1; i < N; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		
		int now = i % 2;
		int ex = i % 2 == 1 ? 0 : 1;
		maxDp[now][1] = max(maxDp[ex][1], maxDp[ex][2]) + a;
		minDp[now][1] = min(minDp[ex][1], minDp[ex][2]) + a;

		maxDp[now][2] = max(maxDp[ex][1], max(maxDp[ex][2], maxDp[ex][3])) + b;
		minDp[now][2] = min(minDp[ex][1], min(minDp[ex][2], minDp[ex][3])) + b;

		maxDp[now][3] = max(maxDp[ex][2], maxDp[ex][3]) + c;
		minDp[now][3] = min(minDp[ex][2], minDp[ex][3]) + c;
	}

	if (N % 2 == 0) {
		max_ans = max(maxDp[1][1], max(maxDp[1][2], maxDp[1][3]));
		min_ans = min(minDp[1][1], min(minDp[1][2], minDp[1][3]));
	} else {
		max_ans = max(maxDp[0][1], max(maxDp[0][2], maxDp[0][3]));
        min_ans = min(minDp[0][1], min(minDp[0][2], minDp[0][3]));
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	cin >> N;
	solution(N);

	cout << max_ans << " " << min_ans;
}
