#include <iostream>
#include <cstring>

using namespace std;

void func(int a[][10]) {
    int b[10][10];
    memcpy(b, a, 10 * 10 * sizeof(int));
    b[3][2] = 1;
    return;
}

void show(int arr[][10]) {
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            cout << arr[i][j] << ' ';
        }
        cout << "\n";
    }
}

int main() {
    int arr[10][10];
    memset(arr, 0, sizeof(arr));
    cout << "=====Before====\n";
    show(arr);

    func(arr); // func의 변경사항이 반영XXX. 

    cout << "\n\n=====After====\n";
    show(arr);

    return 0;
}