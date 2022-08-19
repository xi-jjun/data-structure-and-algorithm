#include <iostream>
#include <set>
#include <string>
#include <cstring>

using namespace std;

int n, k;
int pick[5];
int cards[11];
bool used[11];
set<string> integers;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> k;
	for (int i = 0; i < n; i++) cin >> cards[i];
}

string int_to_str() {
	string ret;
	for (int i = 0; i < k; i++) {
		ret.append(to_string(pick[i]));
	}
	return ret;
}

void solution(int depth) {
	if (depth == k) {
		string number = int_to_str();
		integers.insert(number);
		return;
	}

	for (int i = 0; i < n; i++) {
		if (!used[i]) {
			used[i] = true;
			pick[depth] = cards[i];
			solution(depth + 1);
			used[i] = false;
		}
	}
}

int main() {
	input();
	memset(used, false, sizeof(used));
	solution(0);
	cout << integers.size();
	return 0;
}
