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


int main() {
	for (auto number : arr) {
		ds_set.insert(number);
	}
	for (auto number : ds_set) {
		cout << number;
	}
	
	check_is_present();
	return 0;
}
