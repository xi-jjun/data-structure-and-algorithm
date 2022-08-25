#include <iostream>

typedef long long ll;
using namespace std;

ll N, L;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> L;
}

bool is_double() {
    return (2 * N - L * (L - 1)) % (2 * L) != 0;
}

ll get_start_num() {
    return (2 * N - L * (L - 1)) / (2 * L);
}

bool smaller_than_one() {
    return get_start_num() < 1;
}

bool invalid() {
    return is_double() && smaller_than_one();
}

void print_answer(ll start) {
    for (int cnt = 0; cnt < L; cnt++) {
        cout << (start + cnt) << ' ';
    }
}

void solution() {
    while (true) {
        if (!is_double() && get_start_num() >= 0) {
            ll start = get_start_num();
            print_answer(start);
            return;
        } else if (L >= 100 || invalid()) {
            cout << "-1";
            return;
        }
        L++;
    }
}

int main() {
    input();
    solution();
    return 0;
}