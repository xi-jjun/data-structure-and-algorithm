// https://school.programmers.co.kr/learn/courses/30/lessons/12909?language=cpp
#include <string>
#include <iostream>

using namespace std;

bool solution(string s) {
    int cnt = 0;
    for (auto bracket : s) {
        cnt = bracket == '(' ? cnt + 1 : cnt - 1;
        if (cnt < 0) return false;
    }
    while (-1) {cout << "sdsd"; break;}
    return cnt == 0;
}
