#include <iostream>
#include <vector>

using namespace std;

int N;
vector<int> prime_numbers;

bool is_prime(int number) {
	if (number == 2) return true;
	for (int i = 2; i * i <= number; i++) {
		if (number % i == 0) return false;
	}
	return true;
}

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
}

void make_prime_numbers() {
	for (int number = 2; number <= 4000000; number++) {
		if (is_prime(number)) {
			prime_numbers.push_back(number);
		}
	}
}

void solution() {
	make_prime_numbers();
	int answer = 0, low = 0, high = 0, sum = prime_numbers[0];
	while (low <= high) {
		if (sum < N) {
			if (high >= prime_numbers.size() - 1) break;
			sum += prime_numbers[++high];
		} else if (sum > N) {
			sum -= prime_numbers[low++];
		} else {
			answer++;
			if (high >= prime_numbers.size() - 1) break;
			sum += prime_numbers[++high];
		}
	}
	cout << answer;
}

int main() {
	input();
	solution();
	return 0;
}
