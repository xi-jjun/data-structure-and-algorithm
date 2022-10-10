#include <iostream>
#include <string>
#include <vector>

using namespace std;

struct Foo {
    int x, y;
    string name;
    vector<string> name_list;
};

int main() {
    Foo foos[3];
    foos[0] = {1, 2, "Foo1", vector<string> (5)};
    foos[1] = {10, 20, "Foo2", vector<string> (5)};
    foos[2] = {100, 200, "Foo3", vector<string> (5)};

    Foo temp = foos[0];
    cout << "\n[Before]\n";
    cout << foos[0].x << ' ' << foos[0].y << ' ' << foos[0].name;
    temp.name = "Hello World!";
    temp.x = -101;

    cout << "\n[After]\n";
    cout << foos[0].x << ' ' << foos[0].y << ' ' << foos[0].name;

    // 직접 접근하여 바꿔줘야 한다.
    foos[0].x = -101;
    cout << "\n[After]\n";
    cout << foos[0].x << ' ' << foos[0].y << ' ' << foos[0].name;
    return 0;
}