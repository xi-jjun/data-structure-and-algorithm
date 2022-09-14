// 코딩테스트 연습 >> 2018 KAKAO BLIND RECRUITMENT >> [1차] 다트게임
#include <string>
#include <vector>
#include <cmath>

using namespace std;

bool is_numeric(char c) {
    return '0' <= c && c <= '9';
}

pair<int, bool> find_next_numeric_idx(int search_start, string dartResult) {
    bool is_ten = false; // 현재 탐색하려는 기준의 숫자가 10인지 나타냄
    if (is_numeric(dartResult[search_start])) is_ten = true;

    for (int i = search_start + 1; i < dartResult.length(); i++) {
        if (is_numeric(dartResult[i])) {
            return {i, is_ten}; // 다음 숫자는 i번째에 나타남, 현재 숫자가 10인지 여부
        }
    }
    return {dartResult.length(), is_ten}; // 다음 숫자가 존재x
}

int get_exponent(char option) {
    switch (option) {
        case 'S': return 1;
        case 'D': return 2;
        case 'T': return 3;
        default: return -1;
    }
    return -1;
}

int count_total_numeric(string result) {
    int ret = 0;
    for (int i = 0; i < result.length(); i++) {
        if (get_exponent(result[i]) != -1) ret++;
    }

    return ret;
}

int solution(string dartResult) {
    int answer = 0;
    int MAX_SCORE_LEN = count_total_numeric(dartResult);
    vector<int> scores(MAX_SCORE_LEN);

    int idx = 0, save_idx = 0;
    while (idx < dartResult.length()) {
        pair<int, bool> next_info = find_next_numeric_idx(idx + 1, dartResult);
        int next_idx = next_info.first; // 다음 숫자 나오는 idx
        bool is_ten = next_info.second; // 현재 숫자는 10인지

        int score = is_ten ? 10 : dartResult[idx] - '0';
        idx = is_ten ? idx + 1 : idx;
        int exp = get_exponent(dartResult[idx + 1]);
        if (next_idx - idx == 2) {
            int result = pow(score, exp);
            scores[save_idx] = result;
            idx += 2;
        } else {
            char option = dartResult[idx + 2];

            if (option == '#') {
                scores[save_idx] = -pow(score, exp);
            } else {
                if (save_idx != 0) {
                    scores[save_idx - 1] = 2 * scores[save_idx - 1];
                }
                scores[save_idx] = 2 * pow(score, exp);
            }
            idx += 3;
        }

        save_idx++;
    }

    for (auto score : scores) answer += score;
    
    return answer;
}