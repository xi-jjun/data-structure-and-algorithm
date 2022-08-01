#include <iostream>
#include <string>
#include <stack>

using namespace std;


void solution(int cmd_cnt) {
	int push_num, pop_num, top_num;
	string cmd;
	stack<int> stack;
	while (cmd_cnt--) {
		cin >> cmd;
		if (cmd == "push") {
			cin >> push_num;
			stack.push(push_num);
		} else if (cmd == "pop") {
			if (stack.empty()) cout << "-1\n";
			else {
				cout << stack.top() << "\n";
				stack.pop();
			}
		} else if (cmd == "size") {
			cout << stack.size() << "\n";
		} else if (cmd == "top") {
			cout << (stack.empty() ? -1 : stack.top()) << "\n";
		} else if (cmd == "empty") {
			cout << (stack.empty() ? "1\n" : "0\n");
		}
	}
}


int get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	cin >> N;
	return N;
}

int main() {
	int N = get_input();
	solution(N);
	return 0;
}
