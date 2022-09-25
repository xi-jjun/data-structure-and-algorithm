#include <string>
#include <algorithm>
#include <iostream>

using namespace std;

int main() {
    /*
    대소 비교 기준
    1. 길이가 짧은게 우선순위 더 높음
    2. 길이가 같다면 사전순으로 빠른게 우선순위 더 높음
    */
    string str = "abc";
    string str2 = "ab";
    if (str < str2) cout << "He";
    cout << min(str, str2);
    return 0;
}