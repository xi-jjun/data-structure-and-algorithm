#include <iostream>
#include <map>

using namespace std;

int main() {
    map<int, int> m = {
        {2, 2},
        {1, 2},
        {3, 2}
    };

    auto iter = m.begin();
    while (iter != m.end()) {
        cout << "first : " << iter->first << "\n";
        cout << "second : " << iter->second << "\n";
        cout << "\n";
        ++iter;
    }

    map<int, pair<int, int>> mm = {
        {2, {1, 2}},
        {9, {1, 1}},
        {4, {2, 3}}
    };

    auto iter1 = mm.begin();
    while (iter1 != mm.end()) {
        cout << iter1->first << "\n";
        cout << iter1->second.first <<"\n";
        cout << iter1->second.second << "\n";
        ++iter1;
    }
    return 0;
}