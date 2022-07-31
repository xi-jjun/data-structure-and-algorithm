#include <iostream>

using namespace std;
using ll = long long;

int N, M;
int A[10002];

int solution() {
	int left, right;
	left = right = 0;
	int acc = A[0];
	int ret = 0;

	while (right < N) {
		if (acc < M) {
			acc += A[++right];
		} else if (acc > M) {
			acc -= A[left++];
		} else {
			ret++;
			acc -= A[left++];
      
      if (left > right) {
        right = left;
        acc = A[right];
      }
		}
	}

	return ret;
}

void getInput() {
  ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M;

  for (int i = 0; i < N; i++) {
    cin >> A[i];
  }
}

int main() {
	getInput();
  
	int answer = solution();

	cout << answer;
}
