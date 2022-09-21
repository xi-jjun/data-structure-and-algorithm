#include <iostream>
#include <string>

using namespace std;

int main() {
    string str = "ABCDEFGHIJKLMNOP";
    cout << "original : " << str << "\n";
    string want_to_replaced = "D";
    // start idx , size , replaced
    str.replace(str.find(want_to_replaced), want_to_replaced.length() + 1, "Hello");
    cout << "after : " << str << "\n";

    str.replace(1, 2, "ZZ");
    cout << str << "\n";
    return 0;
}