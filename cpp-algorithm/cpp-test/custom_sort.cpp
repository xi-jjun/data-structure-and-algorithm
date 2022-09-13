#include <iostream>
#include <vector>
#include <string>

using namespace std;

bool comp(int a, int b) {
    return a > b;
}

struct something {
    int x, y;
    string name;

    something(int _x, int _y, string _name) : x(_x), y(_y), name(_name){};
};

bool struct_comp(something o1, something o2) {
    if (o1.x == o2.x) {
        return o1.y > o2.y;
    }
    return o1.x > o2.x;
}

int main() {
    vector<int> v;
    v.push_back(1);
    v.push_back(5);
    v.push_back(3);
    v.push_back(2);
    v.push_back(10);

    sort(v.begin(), v.end(), comp);
    for (auto num : v) cout << num << ' ';

    cout << "\n=== struct sort test ===\n";
    vector<something> list1;
    for (int i = 0; i < 4; i++) {
        something instance(i, i + 20, "str" + to_string(i));
        list1.push_back(instance);
    }
    sort(list1.begin(), list1.end(), struct_comp);
    for (auto o : list1) {
        cout << "x : " << o.x << ", y : " << o.y << ", name : " << o.name << "\n";
    }

    return 0;
}