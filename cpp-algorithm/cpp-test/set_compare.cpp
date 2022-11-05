#include <iostream>
#include <set>

using namespace std;

int main() {
    set<int> s;
    s.insert(1);
    s.insert(73);
    s.insert(3);
    s.insert(88);

    set<int> ss;
    ss.insert(88);
    ss.insert(1);
    ss.insert(3);
    ss.insert(73);

    cout << (ss == s);
    return 0;
}