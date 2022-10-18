#include <cstring>
#include <iostream>

using namespace std;

void show(int arr[][7]) {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 7; j++) {
            cout << arr[i][j] << "  ";
        }
        cout << "\n";
    }
}

int main() {
    int a[4][7];
    memset(a, 0, sizeof(a));
    show(a);

    cout << "\n";

    memset(a[2], -1, sizeof(a[2]));
    show(a);
    return 0;
}