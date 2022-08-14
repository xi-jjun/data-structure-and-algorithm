#include <iostream>
#include <set>

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

pair<int, int> get_reduced_fraction(int a, int b) {
	int dividor = gcd(a, b);
	int child = a / dividor;
	int parent = b / dividor;
	return make_pair(child, parent);
}

int make_pair() {
	pair<int, int> result;
	set<pair<int, int>> fractions;
	for (int parent = 2; parent <= 1000; parent++) {
		for (int child = 1; child < parent; child++) {
			if (child >= parent) continue;
			result = get_reduced_fraction(child, parent);
			fractions.insert(result);
		}
		count[parent] = fractions.size() * 2 + 3;
	}

	return fractions.size() * 2 + 3;
}

void solution() {
	make_pair();
	count[1] = 3;
	while (tc--) {
		cin >> N;
		cout << count[N] << "\n";
	}
}

int main() {
	input();
	solution();
	return 0;
}
