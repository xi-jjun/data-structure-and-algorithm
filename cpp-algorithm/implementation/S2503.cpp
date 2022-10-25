#include <iostream>
#include <string>
#include <tuple>
#include <vector>
#include <set>
#include <cstring>

using namespace std;

struct Number {
    int num;
    int loc;
    char type;
};

const char STRIKE = 'S';
const char BALL = 'B';
const char INVALID = 'X';

int N;
string number; 
int s, b;
set<string> invalid;
vector<string> total;
char selected[3];
bool used[10];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N;
}

void make_cases(int depth) {
    if (depth == 3) {
        string case_answer = selected;
        total.push_back(case_answer);
        return;
    }

    for (int number = 1; number <= 9; number++) {
        if (!used[number]) {
            used[number] = true;
            selected[depth] = number + '0';
            make_cases(depth + 1);
            used[number] = false;
        }
    }
}

void check(string number, int strike_cnt, int ball_cnt) {
    for (string now_case : total) {
        int s = 0, b = 0;
        if (invalid.find(now_case) != invalid.end()) continue;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j && now_case[j] == number[j]) {
                    s++;
                    continue;
                }

                if (now_case[j] == number[i]) b++;
            }
        }

        if (strike_cnt != s || ball_cnt != b) invalid.insert(now_case);
    }
}

void solution() {
    memset(used, false, sizeof(used));
    make_cases(0);

    for (int i = 0; i < N; i++) {
        cin >> number >> s >> b;
        check(number, s, b);
    }

    cout << (504 - invalid.size());
}

int main() {
    input();
    solution();
    return 0;
}