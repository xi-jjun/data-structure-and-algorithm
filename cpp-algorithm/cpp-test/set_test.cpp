#include <iostream>
#include <set>

using namespace std;

set<int> ds_set;
int arr[] = {1, 2, 3, 3, 4, 5, 6, 6, 6, 6, 7, 8};

void check_is_present() {
	set<int> checker;
	checker.insert(1);
	checker.insert(2);
	checker.insert(3);
	
	cout << "\n=== checker has 1,2,3===\n";
	cout << "1 is " << (checker.find(1) != checker.end() ? "presented!\n" : "NO EXISTED\n");	
	cout << "2 is " << (checker.find(2) != checker.end() ? "presented!\n" : "NO EXISTED\n");	
	cout << "3 is " << (checker.find(3) != checker.end() ? "presented!\n" : "NO EXISTED\n");	
	cout << "4 is " << (checker.find(4) != checker.end() ? "presented!\n" : "NO EXISTED\n");	
	cout << "5 is " << (checker.find(5) != checker.end() ? "presented!\n" : "NO EXISTED\n");	
}

void pair_set() {
	cout << "pair set test\n";
	set<pair<int, int>> pair_set;
	pair_set.insert({1, 2});
	pair_set.insert({1, 2});
	pair_set.insert({1, 1});
	pair_set.insert({1, 3});
	pair_set.insert({2, 1});
	pair_set.insert({3, 2});
	pair_set.insert({3, 4});
	pair_set.insert({3, 4});
	pair_set.insert({2, 4});
	pair_set.insert({3, 4});
	pair_set.insert({3, 4});
	
	for (pair<int, int> p : pair_set) {
		cout << p.first << ' ' << p.second << "\n";
	}
	cout << "pair set test end\n";
}

void pair_in_pair_test() {
	cout << "\npair in pair set test\n";
	set<pair<pair<int, int>, pair<int, int>>> pair_pair_set;
	pair_pair_set.insert({{1, 2}, {2, 3}});
	pair_pair_set.insert({{1, 2}, {2, 3}});
	pair_pair_set.insert({{2, 2}, {2, 3}});
	pair_pair_set.insert({{1, 2}, {2, 3}});
	pair_pair_set.insert(make_pair(make_pair(1, 2), make_pair(2, 3)));
	pair_pair_set.insert({{1, 2}, {2, 4}});
	cout << pair_pair_set.size() << "\n";
	cout << "contain : " << (pair_pair_set.find({{1, 2}, {2, 3}}) != pair_pair_set.end()) << "\n";
	cout << "\npair in pair set test END\n";
}

int main() {
	pair_set();
	for (auto number : arr) {
		ds_set.insert(number);
	}
	for (auto number : ds_set) {
		cout << number;
	}
	
	check_is_present();
	pair_in_pair_test();
	return 0;
}
