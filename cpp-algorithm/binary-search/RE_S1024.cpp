#include <iostream>

typedef long long ll;
using namespace std;

int N, L;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> L;
}

bool invalid(int a1, int len) {
    return a1 + len - 1 > N;
}

ll func(int a1, int len) {
    return (ll) len * a1 + L * (L - 1) / 2;
}

void solution() {
    while (L <= 100) {
        int start = 0;
        int end = N;

        while (start <= end) {
            int mid = (start + end) / 2; // a1 

            if (invalid(mid, L) || func(mid, L) > N) end = mid - 1;
            else if (func(mid, L) < N) start = mid + 1;
            else {
                while (L--) cout << mid++ << ' ';
                return;
            }
        }
        L++;
    }
    cout << "-1";
}

int main() {
    input();
    solution();
    return 0;
}