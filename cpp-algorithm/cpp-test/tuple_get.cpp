#include <iostream>
#include <tuple>

using namespace std;

int main() {
    tuple<int, int, int> t;
    t = {1, 10, 100};
    cout << get<1>(t);
    return 0;
}