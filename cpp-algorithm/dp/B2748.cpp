#include <iostream>

using namespace std;
using ll = long long;

ll f[91] = {0, 1, };

ll solution(int N) {
  for (int i = 2; i <= N; i++) {
    f[i] = f[i - 1] + f[i - 2];
  }

  return f[N];
}

int main() {
  int N;
  cin >> N;
  ll answer = solution(N);
  cout << answer;
}
