// https://everenew.tistory.com/104
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int K, N;
vector<int> prime_numbers;
priority_queue<int, vector<int>, greater<int> > numbers;


int solution() {
	int seq = 0;
	int prev_number = 0;
	while (seq != N) {
		while (prev_number == numbers.top()) {
			numbers.pop();
		}

		int now = numbers.top();
		for (int i = 0; i < K; i++) {
			long long added_number = (long long) now * prime_numbers[i];
			if (added_number >= INT32_MAX) break;
			
			numbers.push(added_number);
		}
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
