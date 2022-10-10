#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int a[22] = {3,3,534,6,4,7,3,53,1,7,5,83,756,3,6,357,468,46,864,565,36,12};
    sort(a, a + 22);
    for (auto b : a) cout << b << ' ';

    return 0;
}