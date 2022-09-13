#include <map>
#include <string>
#include <iostream>

using namespace std;

int main() {
    map<string, int> count;
    count["Hello"]++;

    cout << count["World"] << "\n";

    cout << count["Hello"];
}