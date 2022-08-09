#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

pair<int, int> arr[10];

bool sorter(pair<int, int> p1, pair<int, int> p2) {
	if (p1.second == p2.second) {
		return p1.first > p2.first;
	}

	return p1.second < p2.second;
}

int main() {
	arr[1] = {1, 2};
	arr[2] = {2, 4};
	arr[3] = {3, 5};
	arr[4] = {4, 0};
	arr[7] = {7, 1};
	arr[8] = {8, 5};
	arr[9] = {9, 9};
	arr[5] = {5, 4};
	arr[6] = {6, 7};
	for (int i = 0; i < 10; i++) {
		cout << "\n" << i << "'s" << "\n";
		cout << arr[i].first << ' ' << arr[i].second << "\n";
	}

	sort(arr, arr + 10, sorter);

	cout << "\nafter sorting" << "\n";
	for (int i = 0; i < 10; i++) {
		cout << "\n" << i << "'s" << "\n";
		cout << arr[i].first << ' ' << arr[i].second << ' ';
    }
	return 0;
}
