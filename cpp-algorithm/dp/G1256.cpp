// https://velog.io/@youhyeoneee/%EB%B0%B1%EC%A4%80-1256%EB%B2%88-%EC%82%AC%EC%A0%84
#include <iostream>
#include <algorithm>
#include <cstring>
#include <string>

#define MAX_ 1000000000

using namespace std;

string word;
int dp[101][101];
int N, M, K;

void input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> M >> K;
}

void make_dp() {
	memset(dp, -1, sizeof(dp));
	dp[0][0] = 0;
	for (int i = 0; i <= N; i++) {
		for (int j = 0; j <= M; j++) {
			if (i == 0 && j == 0) continue;
			dp[i][j] = i == 0 || j == 0 ? 1 : min(dp[i][j - 1] + dp[i - 1][j], MAX_);
		}
	}
}

void make_word(int a_cnt, int z_cnt, int find_idx) {
	if (a_cnt == 0 || z_cnt == 0) {
		int len = a_cnt == 0 ? z_cnt : a_cnt;
		for (int i = 0; i < len; i++) word = a_cnt == 0 ? word + 'z' : word + 'a';
		return;
	}

	int coverage = dp[a_cnt - 1][z_cnt]; // a + '나머지 조합' 의 개수가 
	if (coverage > find_idx) { // find_idx(찾으려는 단어 순서) 보다 크면 하위를 탐색해야 함. aa + '나머지'
		word += 'a';
		make_word(a_cnt - 1, z_cnt, find_idx);
	} else { // find_idx 보다 작거나 같으면 현재 범위 안에 존재. a + '나머지' ~ az + '나머지' 사이에 있기에 z추가
		word += 'z';
		make_word(a_cnt, z_cnt - 1, find_idx - coverage);
	}
}

void solution() {
	make_dp();

	if (K > dp[N][M]) cout << "-1";
	else {
		make_word(N, M, K - 1);
		cout << word;
	}
}

int main() {
	input();
	solution();
	return 0;
}
