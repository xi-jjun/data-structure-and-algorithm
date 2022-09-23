#include <queue>
#include <vector>
#include <string>
#include <iostream>

using namespace std;

struct Me {
    int id;
    string name;
    int cost;
};

struct cmp {
    bool operator() (Me m1, Me m2) {
        return m1.id < m2.id; // -> 이거 오름차순 아님. pq에서는 큰 순서대로 pop된다.
    }
};

int main() {
    priority_queue<Me, vector<Me>, cmp> pq;
    pq.push({1, "KJJ", 123});
    pq.push({9, "KJJ", 123});
    pq.push({4, "KJJ", 123});
    pq.push({5, "KJJ", 123});
    pq.push({0, "KJJ", 123});
    pq.push({10, "KJJ", 123});
    pq.push({8, "KJJ", 123});

    while (!pq.empty()) {
        cout << "\n=============\n";
        cout << "id : " << pq.top().id << "\n";
        cout << "name : " << pq.top().name << "\n";
        cout << "cost : " << pq.top().cost << "\n";
        pq.pop();
    }
    // id 순서로 정렬되게 했고, 가장 먼저 pop되는 id는 id=10이다.

    return 0;
}