#include <iostream>
#include <set>

using namespace std;

int main() {
    set<int> s;
    s.insert(1);
    s.insert(73);
    s.insert(3);
    s.insert(88);

    if (s.find(1) != s.end()) cout << "1 is in the set\n";
    if (s.find(2) == s.end()) cout << "2 isn't in the set\n";
    return 0;
}