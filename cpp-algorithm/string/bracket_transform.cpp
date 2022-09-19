// 코딩테스트 연습 2020 KAKAO BLIND RECRUITMENT 괄호 변환
// 답 참고함
#include <string>
#include <vector>

using namespace std;

bool valid_bracket_string(string bracket_str) {
    int cnt = 0;
    for (char bracket : bracket_str) {
        cnt = bracket == '(' ? cnt + 1 : cnt - 1;
        if (cnt < 0) return false;
    }
    
    return true;
}

string get_balanced_bracket_str(string str) {
    string ret = "";
    int open_bracket = 0;
    int closed_bracket = 0;
    for (char c : str) {
        if (c == '(') open_bracket++;
        else if (c == ')') closed_bracket++;
        ret += c;
        if (open_bracket == closed_bracket) return ret;
    }
    return ret;
}

string solution(string p) {
    if (p.length() == 0) return "";
    else {
        string u = get_balanced_bracket_str(p);
        string v = p.substr(u.length());
        
        if (valid_bracket_string(u)) {
            u += solution(v);
            return u;
        } else {
            string answer = "(" + solution(v) + ")";
            for (int i = 1; i < u.length() - 1; i++) {
                if (u[i] == '(') answer += ")";
                else answer += "(";
            }
            return answer;
        }
    }
}