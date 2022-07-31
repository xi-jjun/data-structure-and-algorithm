#include <iostream>
#include <algorithm>
#define MAX_ 1000000000;
typedef long long ll;

using namespace std;

ll X, Y;

ll win_rate(ll offset) {
	return 100 * (offset + Y) / (offset + X);
}

ll solution() {
	ll Z = win_rate(0);
	if (Z >= 99) return -1;

	ll left = 0;
	ll right = MAX_;
	ll answer = MAX_;
	while (left <= right) {
		ll offset = (left + right) / 2;
		if (win_rate(offset) > Z) {
			answer = min(answer, offset);
			right = offset - 1;
		} else {
			left = offset + 1;
		}
	}

	return answer;
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	cin >> X >> Y;
}

int main() {
	get_input();
	ll answer = solution();

	cout << answer;
	return 0;
}
