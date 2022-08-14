#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

bool is_prime[1000001];
vector<int> prime;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
}

void make_prime_numbers() {
	memset(is_prime, true, sizeof(is_prime));
	for (int i = 2; i * i <= 1000001; i++) {
		if (!is_prime[i]) continue;
		for (int j = i * i; j <= 1000001; j += i) {
			is_prime[j] = false;
		}
	}

	for (int number = 3; number <= 1000001; number++) {
		if (is_prime[number]) {
			prime.push_back(number);
		}
	}
}

int get_first(int number) {
	for (int prime_number : prime) {
		if (is_prime[number - prime_number]) {
			return prime_number;
		}
	}
	return -1;
}

void solution() {
	make_prime_numbers();
	int N;
	while (1) {
		cin >> N;
		if (N == 0) break;

		int first = get_first(N);
		int second = N - first;
		cout << N << " = " << first << " + " << second << "\n";
	}
}

int main() {
	input();
	solution();
	return 0;
}
