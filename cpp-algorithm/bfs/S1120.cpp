#include <iostream>
#include <string>

using namespace std;

string A, B;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);;
    cin >> A >> B;
}

int min(int a, int b) { return a < b ? a : b; }

int get_diff(string str1, string str2, int start) {
    int ret = 0;
    for (int i = 0; i < str1.length(); i++) {
        if (str1[i] != str2[start + i]) ret++;
    }
    return ret;
}

void solution() {
    int answer = 987654310;
    for (int i = 0; i <= B.length() - A.length(); i++) {
        answer = min(answer, get_diff(A, B, i));
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}