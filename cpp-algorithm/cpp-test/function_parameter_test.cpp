#include <iostream>
#include <vector>
#include <queue>

using namespace std;

void add_numbers_1_to_10(vector<int>& numbers) {
	for (int number = 1; number <= 10; number++) {
		numbers.push_back(number);
	}
}

int main() {
	vector<int> numbers;
	numbers.push_back(12);
	numbers.push_back(11);
	numbers.push_back(43);
	numbers.push_back(-34);

	add_numbers_1_to_10(numbers);
	
	for (auto num : numbers) cout << num << " ";
	return 0;
}
