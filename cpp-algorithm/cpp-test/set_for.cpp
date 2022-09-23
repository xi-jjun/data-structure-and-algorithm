#include <set>
#include <iostream>

using namespace std;

int main() {
    set<int> s;
    s.insert(123);
    s.insert(3);
    s.insert(34);

    set<int>::iterator iter;
    for (iter = s.begin(); iter != s.end(); iter++) {
        cout << *iter << ' ';
    }
    
    cout << "\n";

    return 0;
}