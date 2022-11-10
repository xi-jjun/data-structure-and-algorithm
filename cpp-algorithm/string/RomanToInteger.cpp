#include <stack>
#include <map>

using namespace std;

map<char, int> sym_values = {
    {'I', 1}, {'V', 5}, {'X', 10}, {'L', 50}, {'C', 100},
    {'D', 500}, {'M', 1000}
};

class Solution {
public:
    int romanToInt(string s) {
        stack<char> st;
        int answer = 0;

        for (char c : s) {
            // 1. top empty -> push
            // 2. top보다 c가 등급 높으면 c - top 만큼 정답에 더해주기
            // 3. top보다 c가 등급 낮으면 top 만큼 정답에 더한 후 c push
            if (st.empty()) st.push(c);
            else {
                char ex = st.top(); st.pop();
                if (sym_values[ex] < sym_values[c]) answer += sym_values[c] - sym_values[ex];
                else {
                    answer += sym_values[ex];
                    st.push(c);
                }
            }
        }

        while (!st.empty()) {
            answer += sym_values[st.top()];
            st.pop();
        }

        return answer;
    }
};