#include <iostream>

using namespace std;

pair<int, int> A_ (1, 2);

void pair_same_test() {
	pair<int, int> p1;
	pair<int, int> p2;
	p1 = make_pair(1, 4);
	p2 = make_pair(1, 4);

	if (p1 == p2) cout << "is same\n";
}

void pair_define_test() {
	cout << A_.first << ' ' << A_.second << "\n";
}

int main() {
	pair_same_test();
	pair_define_test();
	return 0;
}
