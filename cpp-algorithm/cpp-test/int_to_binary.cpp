#include <bitset>
#include <iostream>

using namespace std;

int main() {
    int n = 8;
    cout << bitset<8>(123).to_string();
    return 0;
}