#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
	vector<int> arr;
	arr.push_back(23);
	arr.push_back(2);
	arr.push_back(10);
	arr.push_back(9);
	arr.push_back(5);
	arr.push_back(16);
	arr.push_back(3);
	arr.push_back(21);
	arr.push_back(13);
	arr.push_back(32);

	int max_value = *max_element(arr.begin(), arr.end());
	int min_value = *min_element(arr.begin(), arr.end());
	cout << max_value << "\n";
	cout << min_value << "\n";

	return 0;
}
