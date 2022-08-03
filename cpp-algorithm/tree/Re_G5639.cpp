// https://ongveloper.tistory.com/295
#include <iostream>

using namespace std;

int node_size = 0;
int nodes[10001];


void solution(int start_idx, int end_idx) {
	if (start_idx >= end_idx) return;
	if (start_idx == end_idx - 1) {
		cout << nodes[start_idx] << "\n";
		return;
	}

	int mid_idx = start_idx + 1; // 시작 다음부터 sub tree경계 index 탐색하기 위함
	while (mid_idx < end_idx) {
		if (nodes[start_idx] < nodes[mid_idx]) break;
		mid_idx++;
	}

	solution(start_idx + 1, mid_idx); // go left sub tree
	solution(mid_idx, end_idx); // go right sub tree
	cout << nodes[start_idx] << "\n"; // root
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int node;
	while (cin >> node) {
		nodes[node_size++] = node;
	}
}

int main() {
	get_input();
	solution(0, node_size);
	return 0;
}
