#include <iostream>
#include <map>

using namespace std;

map<int, pair<int, int>> tree;

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N; 
	char parent, left, right;
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> parent >> left >> right;
		tree[parent - 'A'].first = left == '.' ? -1 : left - 'A';
		tree[parent - 'A'].second = right == '.' ? -1 : right - 'A';
	}
}

void post_order(int node) {
	if (node != -1) {
		post_order(tree[node].first);
		post_order(tree[node].second);
		cout << (char)(node + 'A');
	}
}

void in_order(int node) {
	if (node != -1) {
		in_order(tree[node].first);
		cout << (char)(node + 'A');
		in_order(tree[node].second);
	}
}

void pre_order(int node) {
	if (node != -1) {
		cout << (char)(node + 'A');
		pre_order(tree[node].first);
		pre_order(tree[node].second);
	}
}

void solution() {
	pre_order(0);
	cout << "\n";
	in_order(0);
	cout << "\n";
	post_order(0);
	cout << "\n";
}

int main() {
	get_input();
	solution();
	return 0;
}
