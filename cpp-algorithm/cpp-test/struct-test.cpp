#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

struct My_struct {
    int map[5][5];
    int max_block;
};

struct Point {
    int x;
    int y;
    int count;

    bool operator()(Point p1, Point p2) {
        if (p1.count == p2.count) return p1.x < p2.x;
        return p1.count < p2.count;
    }
};

void print_point(Point p) {
    cout << "p x = " << p.x << "\n";
    cout << "p y = " << p.y << "\n";
    cout << "p count = " << p.count << "\n";
    cout << "\n";
}

int main() {
    Point p1 = {2, 4, 100};
    priority_queue<Point, vector<Point>, Point> pq;
    pq.push({1, 2, 10});
    pq.push({5, 7, 10});
    pq.push({4, 5, 13});
    pq.push({0, 2, 5});
    pq.push({3, 2, 2});
    cout << "\n====pq test start====\n";
    while (!pq.empty()) {
        print_point(pq.top());
        pq.pop();
    }
    cout << "====pq test end====\n";

    cout << "\n===array in struct test start===\n";
    My_struct s1;
    memset(s1.map, -1, sizeof(s1.map));
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) cout << s1.map[i][j] << ' ';
        cout << "\n";
    }
    cout << "===array in struct test end===\n";
    
    return 0;
}