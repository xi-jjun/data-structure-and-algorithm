#include <iostream>
#include <algorithm>
#include <vector>

typedef long long ll;
using namespace std;

int n, m, A[1000], B[1000];
ll T;
vector<ll> sum_A, sum_B;


void make_sum_list(int *arr, int arr_len, bool type) {
	for (int start = 0; start < arr_len; start++) {
		for (int range_len = 1; range_len <= arr_len - start; range_len++) {
			int idx = start;
			int range = range_len;
			ll sum = 0;
			while (range-- > 0) {
				sum += arr[idx++];
			}
			
			if (type) sum_A.push_back(sum);
			else sum_B.push_back(sum);
		}
	}
}


ll solution() {
	make_sum_list(A, n, true);
	make_sum_list(B, m, false);

	sort(sum_A.begin(), sum_A.end());
	sort(sum_B.begin(), sum_B.end());

	ll answer = 0;
	for (int i = 0; i < sum_A.size(); i++) {
		answer += upper_bound(sum_B.begin(), sum_B.end(), T - sum_A[i]) - lower_bound(sum_B.begin(), sum_B.end(), T - sum_A[i]);
	}

	return answer;
}


void get_input() {
	cin >> T;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> A[i];
	}

	cin >> m;
	for (int i = 0; i < m; i++) {
		cin >> B[i];
	}
}


int main() {
	get_input();

	ll answer = solution();
	cout << answer;
	return 0;
}
