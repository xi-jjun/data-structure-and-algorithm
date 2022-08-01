#include <iostream>
#include <string>
#include <stack>

using namespace std;

string line;
stack<string> stack_, brackets;

int solution() {
	int cnt_1 = 0, cnt_2 = 0;

	for (int i = 0; i < line.length(); i++) {
		if (line[i] == '(') {
			stack_.push("(");
			cnt_1++;
			brackets.push("(");
		} else if (line[i] == '[') {
			stack_.push("[");
			cnt_2++;
			brackets.push("[");
		} else if (line[i] == ')') {
			if (cnt_1 < 1 || brackets.top() == "[") {
				return 0;
			}

			brackets.pop();
			cnt_1--;
			if (stack_.top() == "(") {
				stack_.pop();
				stack_.push("2");
			} else {
				int sum = 0;
				while (stack_.top() != "(") {
					sum += stoi(stack_.top());
					stack_.pop();
				}
				stack_.pop();
				stack_.push(to_string(2 * sum));
			}
		} else {
			if (cnt_2 < 1 || brackets.top() == "(") {
				return 0;
			}

			brackets.pop();
			cnt_2--;
			if (stack_.top() == "[") {
				stack_.pop();
				stack_.push("3");
			} else {
				int sum = 0;
				while (stack_.top() != "[") {
					sum += stoi(stack_.top());
					stack_.pop();
				}
				stack_.pop();
				stack_.push(to_string(3 * sum));
			}
		}
	}

	if (cnt_1 != 0 || cnt_2 != 0) return 0;
	int sum = 0;
	while (!stack_.empty()) {
		sum += stoi(stack_.top());
		stack_.pop();
	}
	return sum;
}

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> line;
}

int main() {
	get_input();
	int answer = solution();
	
	cout << answer;
	return 0;
}
