#include <iostream>
#include <map>

using namespace std;

struct Nums {
    int x, y, z;
};

int main() {
    map<pair<char, char>, Nums> m;
    m[{'A', 'B'}] = {1, 2, 3};
    m[{'A', 'B'}] = {1, 2, 4};

    m[{'B', 'B'}] = {1, 2, 3};

    m[{'C', 'B'}] = {1, 2, 3};

    cout << m[{'A', 'B'}].z << "\n"; // 4가 출력 잘 된다.
}