#include <iostream>
#include <cstring>

using namespace std;

int N, K, p = 2, cnt = 0, seq = 2;
bool erased[1001];

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> K;
}

bool is_prime(int number) {
	for (int i = 3; i * i <= number; i++) {
		if (number % i == 0) return false;
	}
	return true;
}

int find_start_prime_number(int seq) {
	for (int number = seq; number <= N; number++) {
		if (is_prime(number) && !erased[number]) {
			return number;
		}
	}
	return -1;
}

int erase_numbers(int start) {
	for (int i = 1; i * start <= N; i++) {
		if (!erased[start * i]) {
			erased[start * i] = true;
			cnt++;
			if (cnt == K) return start * i;
		}
	}

	return 0;
}

void solution() {
	memset(erased, false, sizeof(erased));
	while (1) {
		p = find_start_prime_number(seq);
		int answer = erase_numbers(p);
		if (answer != 0) {
			cout << answer;
			return;
		}
	}
}

int main() {
	input();
	solution();
	return 0;
}
