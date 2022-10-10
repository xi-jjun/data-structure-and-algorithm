#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct Foo {
    priority_queue<int, vector<int>, greater<int>> pq;
};

int main() {
    Foo foos[5];

    for (int i = 0; i < 5; i++) {
        for (int j = 0; j <= i; j++) {
            foos[i].pq.push(j * 17 % 31);
        }
    }

    for (int i = 0; i < 5; i++) {
        while (!foos[i].pq.empty()) {
            cout << foos[i].pq.top() << ' ';
            foos[i].pq.pop();
        }
        cout << "\n";
    }
}