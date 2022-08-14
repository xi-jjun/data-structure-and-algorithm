#include <iostream>

using namespace std;

int A, B, C, D;

int gcd(int a, int b);

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> A >> B >> C >> D;
}

int lcm(int a, int b) {
	return (a * b) / gcd(a, b);
}

int gcd(int a, int b) {
	if (a % b == 0) return b;
	return gcd(b, a % b);
}

void solution() {
	int parent = lcm(B, D);
	int child = A * (parent / B) + C * (parent / D);
	int dividor = gcd(parent, child);
	cout << (child / dividor) << ' ' << (parent / dividor);
}

int main() {
	input();
	solution();
	return 0;
}
