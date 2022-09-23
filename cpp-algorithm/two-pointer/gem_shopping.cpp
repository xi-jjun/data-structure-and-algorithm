// 코딩테스트 연습 2020 카카오 인턴십 - 보석 쇼핑
// 답 안보고 품.
#include <string>
#include <vector>
#include <set>
#include <map>

using namespace std;

map<string, int> counter;
set<string> total_gem;
set<string> user_gems;

void get_gem_set(vector<string> gems) {
    for (auto gem : gems) total_gem.insert(gem);
}

vector<int> solution(vector<string> gems) {
    get_gem_set(gems);
    
    vector<int> answer;
    answer = {0, 200000};
    
    int start = 0, end = 0;
    user_gems.insert(gems[0]);
    counter[gems[0]] = 1;
    
    while (start <= end) {
        int gem_count = user_gems.size();
        
        if (gem_count < total_gem.size()) {
            end++;
            if (end < gems.size()) {
                user_gems.insert(gems[end]);
                counter[gems[end]]++;
            } else break;
        } else {
            if (end - start < answer[1] - answer[0]) {
                answer = {start, end};
            } else if (end - start == answer[1] - answer[0]) {
                if (start < answer[0]) answer = {start, end};
            }

            counter[gems[start]]--;
            if (counter[gems[start]] <= 0) user_gems.erase(gems[start]);
            start++;
        }
    }
    answer[0]++;
    answer[1]++;
    return answer;
}