#include <iostream>
#include <vector>

using namespace std;

struct User {
    bool is_user;
};

int main() {
    vector<User> v;
    v.push_back({false});
    v.push_back({false});
    v.push_back({true});
    v.push_back({false});

    for (auto a : v) cout << a.is_user << ' ';
    cout << "\n";
    v[2].is_user = false;

    for (auto a : v) cout << a.is_user << ' ';
    return 0;
}