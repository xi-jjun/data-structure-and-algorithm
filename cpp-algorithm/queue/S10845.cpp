#include <iostream>
#include <queue>
#include <string>

using namespace std;
int N;


void solution() {
	int push_num, front_num, back_num, pop_num;
	queue<int> q;
	for (int i = 0; i < N; i++) {
		string cmd;
		cin >> cmd;
		if (cmd[1] == 'u') { // push
			cin >> push_num;
			q.push(push_num);
		} else if (cmd[1] == 'o') {
			if (q.empty()) pop_num = -1;
			else {
				pop_num = q.front();
				q.pop();
			}
			cout << pop_num << "\n";
		} else if (cmd[1] == 'i') { // size
			cout << q.size() << "\n";
		} else if (cmd[1] == 'm') { // empty
			if (q.empty()) cout << "1\n";
			else cout << "0\n";
		} else if (cmd[1] == 'r') { // front
			front_num = q.empty() ? -1 : q.front();
			cout << front_num << "\n";
		} else if (cmd[1] == 'a') { // back 
			back_num = q.empty() ? -1 : q.back();
			cout << back_num << "\n";
		}
	}
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
}


int main() {
	get_input();
	solution();
	return 0;
}
