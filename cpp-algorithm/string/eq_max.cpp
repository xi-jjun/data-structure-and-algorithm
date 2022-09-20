#include <string>
#include <vector>
#include <stack>
#include <algorithm>
#include <cmath>

using namespace std;

typedef long long ll;

string eq, original_eq;
vector<ll> ans_candidate;
bool used_op[3] = {false};
char op_codes[3];
const char OPCODE[3] = {'+', '-', '*'};

ll calculate(ll a, ll b, char op) {
    switch (op) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        default:
            return 1;
    }
}

bool is_numeric(char c) {
    return '0' <= c && c <= '9';
}

ll get_result() {
    /*
    1. number, operator 분리. vector에 각각 저장
    2. 우선순위에 해당하는 문자들 먼저 계산하기
    3. 계산한 문자는 제거. erase쓰면 어차피 중간꺼 제거되면서 index 한 칸씩 줄어듦
    */
    vector<ll> numbers;
    vector<char> operators;
    string num;

    for (int i = 0; i < eq.length(); i++) {
        if (!is_numeric(eq[i])) {
            numbers.push_back(stoi(num));
            num = "";
            operators.push_back(eq[i]);
        } else {
            string str(1, eq[i]);
            num += str;
        }
    }
    numbers.push_back(stoi(num));
    
    for (char op : op_codes) {
        for (int i = 0; i < operators.size(); i++) {
            if (op == operators[i]) {
                ll first = numbers[i];
                ll second = numbers[i + 1];
                numbers[i] = calculate(first, second, op);
                numbers.erase(numbers.begin() + i + 1);
                operators.erase(operators.begin() + i);
                i--;
            }
        }
    }
    
    if (numbers.size() == 1) return numbers[0];
    else {
        return calculate(numbers[0], numbers[1], operators[0]);
    }
}

void solve(int depth) {
    if (depth == 3) {
        eq = original_eq;
        ll result = get_result();
        ans_candidate.push_back(abs(result));
        return;
    }
    
    for (int i = 0; i < 3; i++) {
        if (!used_op[i]) {
            used_op[i] = true;
            op_codes[depth] = OPCODE[i];
            solve(depth + 1);
            used_op[i] = false;
        }
    }
}

ll solution(string expression) {
    original_eq = expression;
    eq = expression;
    solve(0);
    ll answer = 0;
    for (auto ans : ans_candidate) {
        answer = max(answer, ans);
    }
    return answer;
}