#include <iostream>
#include <deque>

using namespace std;

int main() {
    deque<int> dq;
    dq.push_front(111);
    dq.push_front(2222);
    dq.push_back(999);

    // 2222 111 999
    for (auto a : dq) cout << a << ' '; 
    // index로도 접근이 가능하다.

    dq[0] = 41;
    cout << "\n";
    for (auto a : dq) cout << a << ' '; 
    return 0;
}