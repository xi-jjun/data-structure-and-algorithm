#include <iostream>
#include <algorithm>

using namespace std;

int N, k, number;
vector<int> arr;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> k;
    for (int i = 0; i < N; i++) {
        cin >> number;
        arr.push_back(number);
    }
}

bool desc(int a, int b) {
    return a > b;
}

int main() {
    input();
    sort(arr.begin(), arr.end(), desc);
    cout << arr[k - 1];
    return 0;
}