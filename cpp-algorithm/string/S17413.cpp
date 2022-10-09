#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    string line;
    getline(cin, line);

    int idx = 0;
    string answer = "", acc = "";
    while (idx < line.length()) {
        if (line[idx] == '<') {
            reverse(acc.begin(), acc.end());
            answer += acc;
            acc = "";

            while (line[idx] != '>') {
                answer += line[idx++];
            }
            answer += '>';
        } else if (line[idx] == ' ') {
            reverse(acc.begin(), acc.end());
            answer += acc + ' ';
            acc = "";
        } else {
            acc += line[idx];
        }
        idx++;
    }

    if (acc != "") {
        reverse(acc.begin(), acc.end());
        answer += acc;
    }

    cout << answer;
    return 0;
}