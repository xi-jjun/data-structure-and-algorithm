#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool desc(int a, int b) { return a > b; }

int main() {
	vector<int> v;
	v.push_back(1);
	v.push_back(2);
	v.push_back(2);
	v.push_back(4);
	v.push_back(7);
	v.push_back(7);
	v.push_back(11);
	v.push_back(11);
	v.push_back(13);
	v.push_back(7);
	v.push_back(8);
	v.push_back(34);
	v.push_back(32);
	v.push_back(23);
	v.push_back(24);
	v.push_back(18);

	sort(v.begin(), v.end());

	for (auto num : v) cout << num << " ";
	cout << "\n";

	cout << "value 7's upper_bound : " << upper_bound(v.begin(), v.end(), 7) - v.begin() << "\n";
	cout << "value 7's lower_bound : " << lower_bound(v.begin(), v.end(), 7) - v.begin() << "\n";

	cout << "desc arr\n";
	sort(v.begin(), v.end(), desc);	
	for (auto num : v) cout << num << " ";
	cout << "value 7's upper_bound : " << upper_bound(v.begin(), v.end(), 7) - v.begin() << "\n";
	cout << "value 7's upper_bound : " << upper_bound(v.begin(), v.end(), 7) - v.begin() << "\n";

	return 0;
}
