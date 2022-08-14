#include <iostream>

using namespace std;

int tc, N;
int count[1001];

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> tc;
}

int gcd(int a, int b) {
	if (a % b == 0) return b;
	return gcd(b, a % b);
}

void make_count() {
	int cnt = 0;
	for (int parent = 2; parent <= 1000; parent++) {
		for (int child = 1; child < parent; child++) {
			if (gcd(parent, child) == 1) cnt++;
		}
		count[parent] = cnt;
	} 
}

void solution() {
	make_count();
	while (tc--) {
		cin >> N;
		cout << (count[N] * 2 + 3) << "\n";
	}
}

int main() {
	input();
	solution();
	return 0;
}
