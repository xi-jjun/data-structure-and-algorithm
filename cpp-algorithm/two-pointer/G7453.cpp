// https://loosie.tistory.com/553 참고
#include <iostream>
#include <vector>
#include <algorithm>

typedef long long ll;
using namespace std;

int N;
ll A[4000], B[4000], C[4000], D[4000], answer = 0;
vector<int> AB, CD;


void combine() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			AB.push_back(A[i] + B[j]);
			CD.push_back(C[i] + D[j]);
		}
	}
}


void search() {
	ll low_ptr = 0;
	ll high_ptr = N * N - 1;

	while (low_ptr < N * N && high_ptr > -1) {
		ll sum = AB[low_ptr] + CD[high_ptr];

		if (sum < 0) {
			low_ptr++;
		} else if (sum > 0) {
			high_ptr--;
		} else {
			ll std_AB = AB[low_ptr];
			ll std_CD = CD[high_ptr];
			ll AB_same_cnt = 0;
			ll CD_same_cnt = 0;

			while (low_ptr < N * N && AB[low_ptr] == std_AB) {
				AB_same_cnt++;
				low_ptr++;
			}

			while (high_ptr > -1 && CD[high_ptr] == std_CD) {
				CD_same_cnt++;
				high_ptr--;
			}

			answer += AB_same_cnt * CD_same_cnt;
		}
	}
}


void solution() {
	// 1. A, B 배열 합쳐서 새로 하나 만들기
	// 2. C, D ''
	// 3. AB, CD 배열 각각 sort
	// 4. two ptr로 합 0이 되는 것 탐색.
	combine();
	
	sort(AB.begin(), AB.end());
	sort(CD.begin(), CD.end());

	search();
}


void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> A[i] >> B[i] >> C[i] >> D[i];
	}
}

int main() {
	get_input();
	
	solution();

	cout << answer;
	return 0;
}
