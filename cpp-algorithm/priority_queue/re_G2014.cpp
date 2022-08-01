// https://everenew.tistory.com/104
#include <iostream>
#include <queue>
#include <vector>

typedef long long ll;
using namespace std;

int K, N;
vector<int> prime_numbers;
priority_queue<int, vector<int>, greater<int> > numbers;


void erase_dup(int dup_number) {
	while (dup_number == numbers.top()) {
		numbers.pop();
	}
}

void add_prime_numbers_x_std_num(int std_num) {
	for (int i = 0; i < K; i++) {
		ll added_num = (ll) std_num * prime_numbers[i];
		if (added_num >= INT32_MAX) return;

		numbers.push(added_num);
	}
}

int solution() {
	int seq = 0;
	int prev_number = 0;
	while (seq != N) {
		erase_dup(prev_number);

		int now = numbers.top();

		add_prime_numbers_x_std_num(now);

		seq++;
		prev_number = now;
		numbers.pop();
	}

	return prev_number;
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> K >> N;
	int number;
	for (int i = 0; i < K; i++) {
		cin >> number;
		prime_numbers.push_back(number);
		numbers.push(number);
	}
}

int main() {
	get_input();

	int answer = solution();
	cout << answer;
	return 0;
}
