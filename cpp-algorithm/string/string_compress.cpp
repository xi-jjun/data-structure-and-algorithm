#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

#define INF 10000000

using namespace std;

int solution(string s) {
    int answer = INF;
    int len = 0;
    while (len++ < s.length() / 2) {
        string str = "";
        string ex = "", now;
        int again_cnt = 0;
        for (int i = 0; i < s.length(); i += len) {
            now = s.substr(i, len);

            if (now != ex) {
                if (again_cnt > 1) {
                    str = str + to_string(again_cnt) + ex;
                } else if (again_cnt == 1) {
                    str += ex;
                }
                ex = now;
                again_cnt = 1;
            } else again_cnt++;
            
            if (i + len >= s.length()) {
                if (again_cnt > 1) {
                    str = str + to_string(again_cnt) + ex;
                } else if (again_cnt == 1) {
                    str += ex;
                }
                ex = now;
                again_cnt = 1;
            }
        }
        
        if (answer > str.length() && str.length() != 0) answer = str.length();
    }
    return answer == INF ? 1 : answer;
}

int main() {
    int a = solution("s");
    cout << "answer : " << a;
    return 0;
}