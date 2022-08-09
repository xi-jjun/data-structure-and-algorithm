#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

string numbers;

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> numbers;
}

void solution() {
	sort(numbers.begin(), numbers.end(), greater<>());
	if (numbers[numbers.size() - 1] != '0') {
		cout << "-1";
	} else {
		int sum = 0;
		for (auto number : numbers) {
			sum += number - '0';
		}

		if (sum % 3 == 0) cout << numbers;
		else cout << "-1";
	}
}

int main() {
	get_input();
	solution();
	return 0;
}
