#include <iostream>
#include <typeinfo>
#include <string>

using namespace std;

int main() {
    string str = "abc";
    cout << typeid(str[1]).name() << "\n"; // c
    cout << typeid(str).name();
    return 0;
}