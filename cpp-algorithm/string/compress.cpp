// 코딩테스트 연습 >> 2018 KAKAO BLIND RECRUITMENT >> [3차] 압축
#include <string>
#include <vector>
#include <map>

using namespace std;

int dictionary_idx = 1;
map<string, int> dictionary;

void reset_dictionary() {
    string word;
    for (int i = 0; i < 26; i++) {
        char alphabet = 'A' + i;
        word = alphabet;
        dictionary[word] = dictionary_idx;
        dictionary_idx++;
    }
}

bool is_existed(string word) {
    return dictionary.find(word) != dictionary.end();
}

int get_valid_len(string msg, int start) {
    int len = 1;
    while (len < msg.length()) {
        string check = msg.substr(start, len);
        if (is_existed(check)) {
            len++;
        } else return len - 1;
    }
    
    return len;
}

vector<int> solution(string msg) {
    reset_dictionary();
    
    int idx = 0;
    vector<int> answer;
    while (idx < msg.length()) {
        int valid_len = get_valid_len(msg, idx);
        
        string word = msg.substr(idx, valid_len);
        answer.push_back(dictionary[word]);
        
        if (idx + valid_len + 1<= msg.length()) {
            string registered_word = word + msg[idx + valid_len];
            dictionary[registered_word] = dictionary_idx;
            dictionary_idx++;
        }
        idx += + valid_len;
    }
    
    return answer;
}