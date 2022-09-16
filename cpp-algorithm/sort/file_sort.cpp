#include <string>
#include <vector>
#include <cctype>
#include <algorithm>

#define LIMIT_NUMBER_LENGTH 5

using namespace std;

struct file {
    string HEAD;
    string NUMBER;
    string TAIL;
    int seq;
    
    file(string _a, string _b, string _c) : HEAD(_a), NUMBER(_b), TAIL(_c) {};
};

string str_lower(string str) {
    string ret;
    for (auto c : str) {
        ret += tolower(c);
    }
    return ret;
}

bool comp(file f1, file f2) {
    if (str_lower(f1.HEAD) == str_lower(f2.HEAD)) {
        if (stoi(f1.NUMBER) == stoi(f2.NUMBER)) {
            return f1.seq < f2.seq;
        }
        return stoi(f1.NUMBER) < stoi(f2.NUMBER);
    }
    
    return str_lower(f1.HEAD).compare(str_lower(f2.HEAD)) < 0;
}

bool is_numeric(char c) {
    return '0' <= c && c <= '9';
}

pair<string, int> parsing_HEAD(string file_name) {
    string HEAD(1, file_name[0]);
    for (int i = 1; i < file_name.length(); i++) {
        if (is_numeric(file_name[i])) {
            return {HEAD, i};
        }
        HEAD += file_name[i];
    }
    return {"-1", -1};
}

pair<string, int> parsing_NUMBER(string file_name, int start_idx) {
    int num_cnt = 1;
    string NUMBER(1, file_name[start_idx]);
    for (int i = start_idx + 1; i < file_name.length(); i++) {
        if (!is_numeric(file_name[i])) return {NUMBER, i};
        
        NUMBER += file_name[i];
        num_cnt++;
        
        if (num_cnt == LIMIT_NUMBER_LENGTH) return {NUMBER, i + 1};
    }
    
    return {NUMBER, file_name.length()};
}

string parsing_TAIL(string file_name, int start_idx) {
    string TAIL;
    if (start_idx < file_name.length()) {
        TAIL = file_name.substr(start_idx);
    }
    return TAIL;
}

file parsing_file(string file_name) {
    pair<string, int> first_result = parsing_HEAD(file_name);
    string HEAD = first_result.first;
    int number_start_idx = first_result.second;
    
    pair<string, int> second_result = parsing_NUMBER(file_name, number_start_idx);
    string NUMBER = second_result.first;
    int tail_start_idx = second_result.second;
    
    string TAIL = parsing_TAIL(file_name, tail_start_idx);
    
    return file(HEAD, NUMBER, TAIL);
}

string get_file_name(file file_info) {
    return file_info.HEAD + file_info.NUMBER + file_info.TAIL;
}

vector<string> solution(vector<string> files) {
    vector<string> answer;
    vector<file> file_list;
    int seq = 0;
    for (string file_name : files) {
        file f = parsing_file(file_name);
        f.seq = seq++;
        // cout << "HEAD : " << f.HEAD << "\n";
        // cout << "NUMBER : " << f.NUMBER << "\n";
        // cout << "TAIL : " << f.TAIL << "\n";
        // cout << "SEQ : " << f.seq << "\n\n";
        
        file_list.push_back(f);
    }
    sort(file_list.begin(), file_list.end(), comp);
    
    for (auto file_info : file_list) {
        answer.push_back(get_file_name(file_info));
    }
    return answer;
}
