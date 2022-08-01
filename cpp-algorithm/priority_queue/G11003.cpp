#include <iostream>
#include <queue>
#include <vector>
#include <utility>
using namespace std;

int N, L;

priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > min_heap;


void solution() {
	int A;
	for (int i = 1; i <= N; i++) {
		cin >> A;
		min_heap.push(make_pair(A, -i));
		while (-min_heap.top().second < i - L + 1) min_heap.pop();

		cout << min_heap.top().first << " ";
	}
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N >> L;
}

int main() {
	get_input();
	solution();
	return 0;
}
