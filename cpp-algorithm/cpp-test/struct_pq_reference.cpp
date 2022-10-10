#include <queue>
#include <iostream>

using namespace std;

struct Foo {
    priority_queue<int> pq;
    int a;
};

Foo foos[5];

int main() {
    for (int i = 0; i < 5; i++) {
        foos[i].a = i;
        if (i % 2 == 0) foos[i].pq.push(i * 10);
    }

    for (int i = 0; i < 5; i++) {
        Foo example = foos[i];
        example.pq.push(1212); // example에 어떤 짓을 해도 foos[i]에는 영향x
        foos[i] = example;
        cout << foos[i].pq.size() << "\n";
    }

    return 0;
}