#include <string>
#include <iostream>

using namespace std;

int main() {
    cout << string(1, 'D');
    cout << string(1, 9); // 무시됨

    return 0;
}