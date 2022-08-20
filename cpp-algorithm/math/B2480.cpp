#include <iostream>
#include <algorithm>
#include <set>

using namespace std;

set<int> container;
int a, b, c;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> a >> b >> c;
	container.insert(a);
	container.insert(b);
	container.insert(c);
}

void solution() {
	if (container.size() == 1) {
		cout << (1000 * a + 10000);
	} else if (container.size() == 2) {
		int two = a == b ? a : (a == c ? a : b);
		cout << (100 * two + 1000);
	} else {
		int one = max(a, max(b, c));
		cout << (one * 100);
	}
}

int main() {
	input();
	solution();
	return 0;
}
