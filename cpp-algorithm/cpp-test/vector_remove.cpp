#include <vector>
#include <iostream>

using namespace std;

void show(vector<int> v) {
    for (int a : v) cout << a << ' ';
}

int main() {
    vector<int> v;
    v.push_back(1);
    v.push_back(2);
    v.push_back(3);
    v.push_back(4);
    v.push_back(5);
    v.push_back(6);

    show(v);
    cout << "\n";

    int i = 0;
    v.erase(v.begin() + i); // i번째 원소를 제거.
    show(v);
    cout << "\n4번째 원소 : " << v[4];

    return 0;
}