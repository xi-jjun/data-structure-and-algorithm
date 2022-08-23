#include <iostream>
#include <algorithm>

using namespace std;
typedef long long ll;

int T, A, B;

ll gcd(ll a, ll b) {
    if (a % b) return gcd(b, a % b);
    return b;
}

ll lcs(ll a, ll b) {
    return (a * b) / gcd(a, b);
}

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> T;
}

void solution() {
    for (int i = 0; i < T; i++) {
        cin >> A >> B;
        cout << lcs(max(A, B), min(A, B)) << "\n";
    }
}

int main() {
    input();
    solution();
    return 0;
}