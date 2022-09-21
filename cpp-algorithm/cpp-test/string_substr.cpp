#include <string>
#include <iostream>

using namespace std;

int main() {
    string str = "My name is Jaejun";
    cout << str.substr(3, 3); // start, length
    //  nam 출력됨

    return 0;
}