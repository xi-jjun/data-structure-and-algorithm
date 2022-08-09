// https://nanyoungkim.tistory.com/115
#include <iostream>

using namespace std;


void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
}

void find_multiple(int n) {
	long long a = 1;
	int cnt = 1;
	while (1) {
		if (a % n == 0) {
			cout << cnt << "\n";
			return;
		}
		cnt++;
		a = (a * 10 + 1) % n;
	}
}

void solution() {
	int n;
	while (cin >> n) {
		find_multiple(n);
	}
}


int main() {
	get_input();
	solution();
	return 0;
}
