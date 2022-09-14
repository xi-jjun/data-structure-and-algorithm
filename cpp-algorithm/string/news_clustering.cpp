// 코딩테스트 연습 2018 KAKAO BLIND RECRUITMENT [1차] 뉴스 클러스터링
#include <string>
#include <map>
#include <cctype>
#include <set>
#include <vector>
#include <algorithm>
/*
bucket : vector여야 함. 중복되는 다중원소들 존재해야하기 때문
counter : map. key=keyword : value=개수

1. str1, str2 lowerCase() 적용
2. 각 keyword 추출.
    2-1. keyword 검증
    2-2. if 검증(is_alphabet) OK, then add keyword in bucket
3. 각 bucket1,2의 원소들을 카운팅 해준다 => counter1, 2 사용. 
4. counting 정보로 교집합, 합집합 구하기
    min_cnt = min(cnt1[KEYWORD], cnt2[KEYWORD]) + ... 같은 KEYWORD에서 최소값 : 교집합 개수(둘 다 존재하는 KEYWORD여야 함)
    max_cnt = max(cnt1[KEYWORD], cnt2[KEYWORD]) + ... 같은 KEYWORD에서 최대값 : 합집합 개수(둘 중 하나에만 존재하는 KEYWORD여야 함)
5. 유사도 구하기
*/
using namespace std;
set<string> total;

string make_lower(string str) {
    string ret;
    for (char character : str) {
        ret += tolower(character);
    }
    
    return ret;
}

bool is_char(char c) {
    return 'a' <= c && c <= 'z';
}

bool valid(string str) {
    return is_char(str[0]) && is_char(str[1]);
}

vector<string> extract_keyword(string str) {
    vector<string> ret;
    for (int i = 0; i < str.length() - 1; i++) {
        string keyword = str.substr(i, 2);

        if (valid(keyword)) {
            total.insert(keyword);
            ret.push_back(keyword);
        }
    }
    
    return ret;
}

map<string, int> make_counter(vector<string> list) {
    map<string, int> ret;
    for (auto str : list) {
        ret[str]++;
    }
    
    return ret;
}

int get_intersection_count(map<string, int> str1_counter, map<string, int> str2_counter) {
    int ret = 0;
    for (auto str : total) {
        if (str1_counter[str] && str2_counter[str]) {
            ret += min(str1_counter[str], str2_counter[str]);
        }
    }
    return ret;
}

int get_union_count(map<string, int> str1_counter, map<string, int> str2_counter) {
    int ret = 0;
    for (auto str : total) {
        ret += max(str1_counter[str], str2_counter[str]);
    }
    return ret;
}

int solution(string str1, string str2) {
    int answer = 0;
    str1 = make_lower(str1);
    str2 = make_lower(str2);
    
    vector<string> str1_parsed = extract_keyword(str1);
    vector<string> str2_parsed = extract_keyword(str2);
    
    map<string, int> str1_counter = make_counter(str1_parsed);
    map<string, int> str2_counter = make_counter(str2_parsed);
    
    int intersection_cnt, union_cnt;
    intersection_cnt = get_intersection_count(str1_counter, str2_counter);
    union_cnt = get_union_count(str1_counter, str2_counter);
    
    cout << intersection_cnt << "\n";
    cout << union_cnt << "\n";
    
    if (union_cnt == 0) answer = 65536;
    else answer = (int) 65536 * intersection_cnt / union_cnt;
    
    return answer;
}