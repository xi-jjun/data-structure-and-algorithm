#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

const int INF = 987654321;
int N, M;
int A[100002];

int solution() {
	int start, end;
	start = end = 0;
	int acc = A[0];

	int ret = INF;
	while (end < N) {
		if (acc < M) {
			acc += A[++end];
		} else {
			ret = min(ret, abs(end - start) + 1);
			acc -= A[start++];
			
			if (start > end) {
				end = start;
				acc = A[end];
			}
		}
	}

	return ret == INF ? 0 : ret;
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
