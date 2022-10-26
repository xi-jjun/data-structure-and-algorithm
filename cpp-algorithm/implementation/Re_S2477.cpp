#include <iostream>
#include <deque>

using namespace std;

int K;
int dir, len;
int max_value = 0;
int max_value_idx = 0;
deque<int> dq;

int max(int a, int b) { return a > b ? a : b; }

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> K;
    for (int i = 0; i < 6; i++) {
        cin >> dir >> len;
        dq.push_back(len);
        if (max_value < len) {
            max_value = len;
            max_value_idx = i;
        }
    }
}

void solution() {
    int leftOfMaxValue = dq[(max_value_idx + 5) % 6];
    int rightOfMaxValue = dq[(max_value_idx + 1) % 6];

    int max_other_len = max(leftOfMaxValue, rightOfMaxValue);

    if (leftOfMaxValue < rightOfMaxValue) {
        int min_len = rightOfMaxValue - leftOfMaxValue;
        int min_other_len = max_value - dq[(max_value_idx + 4) % 6];
        cout << ((max_value * max_other_len - min_len * dq[(max_value_idx + 4) % 6]) * K) ;
    } else {
        int min_len = leftOfMaxValue - rightOfMaxValue;
        int min_other_len = max_value - dq[(max_value_idx + 2) % 6];
        cout << ((max_value * max_other_len - min_len * dq[(max_value_idx + 2) % 6]) * K);
    }
}

int main() {
    input();
    solution();
    return 0;
}