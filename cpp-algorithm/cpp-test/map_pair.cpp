#include <map>
#include <iostream>

using namespace std;

int main() {
    map<int, pair<int, int>> m;
    m[3].first = 1;

    auto iter = m.begin();
    while (iter != m.end()) {
        cout << "first : " << iter->first << "\n";
        cout << "pair : " << iter->second.first << ", " << iter->second.second << "\n\n";
        ++iter;
    }

    return 0;
}