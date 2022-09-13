#include <iostream>
#include <string>

using namespace std;

typedef char(*arrPointer)[4];

arrPointer func(string str) {
    static char a[4][4]; // static 안해주면 func 끝나면서 local stack memory 해제하면서 a 가 사라진다. 따라서 적용이 안된다.
    int row = 0;
    for (auto c : str) {
        for (int i = 0; i < 4; i++)
            a[row][i] = c;
        row++;
    }

    return a;
}

int main() {
    arrPointer m = func("abcd");
    cout << m << "\n";
    cout << m[3];

    return 0;
}