#include <iostream>
#include <vector>

using namespace std;

void show(vector<int> v) {
    for (auto a : v) {
        cout << a << ", ";
    }
    cout << "\n";
}

int main() {
    vector<int> v;
    for (int i = 1; i <= 13; i++) {
        v.push_back(i);
    }

    cout << "===init===\n";
    show(v);

    // 배열의 3번째 요소부터 7번째 요소를 가진다.
    auto first = v.begin() + 3;
    auto last = v.begin() + 8;

    vector<int> new_v(first, last);

    cout << "\n===cut===\n";
    show(new_v);

    new_v[3] = -111;

    show(v);
    show(new_v);

    return 0;
}