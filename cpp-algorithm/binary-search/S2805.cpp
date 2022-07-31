#include <iostream>
#include <algorithm>
#define MAX_ 2000000000

typedef long long ll;
using namespace std;

ll N, M, max_h;
ll heights[1000001];

ll can_get_cnt(ll standard) {
	ll ret = 0;
	for (int i = 0; i < N; i++) {
		if (heights[i] >= standard) {
			ret += heights[i] - standard;
		}
	}
	return ret;
}

ll solution() {
	ll low = 0;
	ll high = max_h;

	ll ans = 0;
	while (low <= high) {
		ll height = (low + high) / 2;
		if (can_get_cnt(height) >= M) {
			low = height + 1;
			ans = max(ans, height);
		} else {
			high = height - 1;
		}
	}

	return ans;
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> heights[i];
		max_h = max(max_h, heights[i]);
	}
}

int main() {
	get_input();
	ll answer = solution();
	cout << answer;
	return 0;
}
