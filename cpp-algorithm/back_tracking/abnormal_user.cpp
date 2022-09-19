// 코딩테스트 연습 2019 카카오 개발자 겨울 인턴십 불량 사용자
// 유형 참고
#include <string>
#include <vector>
#include <set>

using namespace std;

int answer, answer_set_idx = 0;
vector<vector<string>> answer_set_list(19, vector<string> (19));
vector<string> user_id_list;
vector<string> banned_id_list;
string banned_list[9];
bool checked[9] = {false};

bool is_similar(string banned_id, string user_id) {
    if (banned_id.length() != user_id.length()) return false;
    
    for (int i = 0; i < user_id.length(); i++) {
        if (banned_id[i] == '*') continue;
        if (banned_id[i] != user_id[i]) return false;
    }
    
    return true;
}

bool is_dup() { 
    for (int k = 0; k < answer; k++) {
        set<string> total;
        for (int i = 0; i < banned_id_list.size(); i++) {
            total.insert(banned_list[i]);
            total.insert(answer_set_list[k][i]);
        }
        
        if (total.size() == banned_id_list.size()) return true;
    }

    return false;
}

void back_tracking(int depth) {
    if (depth == banned_id_list.size()) {
        if (is_dup()) return;
        
        for (int i = 0; i < banned_id_list.size(); i++) {
            answer_set_list[answer_set_idx][i] = banned_list[i];
        }
        answer_set_idx++;
        answer++;
        return;
    }
    
    for (int i = 0; i < user_id_list.size(); i++) {
        if (!checked[i] && is_similar(banned_id_list[depth], user_id_list[i])) {
            checked[i] = true;
            banned_list[depth] = user_id_list[i];
            back_tracking(depth + 1);
            checked[i] = false;
        }
    }
}

int solution(vector<string> user_id, vector<string> banned_id) {
    user_id_list = user_id;
    banned_id_list = banned_id;
    
    back_tracking(0);

    return answer;
}