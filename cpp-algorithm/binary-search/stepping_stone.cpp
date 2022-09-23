// 코딩테스트 연습 2019 카카오 개발자 겨울 인턴십 - 징검다리 건너기
// reference : https://0xd00d00.github.io/2021/08/02/stepping_stone.html
#include <string>
#include <vector>
#include <algorithm>

#define INF 200000001

using namespace std;

pair<int, int> get_min_max(vector<int> stones) {
    int min_ = INF, max_ = 0;
    for (int stone : stones) {
        min_ = min(min_, stone);
        max_ = max(max_, stone);
    }
    
    return {min_, max_};
}

int count_zero(int standard, vector<int> stones) {
    int seq_cnt = 0, max_seq = 0;
    for (int i = 0; i < stones.size(); i++) {
        if (stones[i] - standard < 0) {
            stones[i] = 0;
            seq_cnt++;
        } else {
            stones[i] -= standard;
            seq_cnt = 0;
        }
        max_seq = max(max_seq, seq_cnt);
    }
    return max_seq;
}

int solution(vector<int> stones, int k) {
    int answer = 0;
    int min_stone, max_stone;
    pair<int, int> result = get_min_max(stones);
    min_stone = result.first;
    max_stone = result.second;
    
    while (min_stone <= max_stone) {
        int standard = (min_stone + max_stone) / 2;
        
        int cnt_0 = count_zero(standard, stones);
        if (cnt_0 >= k) {
            max_stone = standard - 1;
        } else {
            answer = max(answer, standard);
            min_stone = standard + 1;
        }
    }
    
    return answer;
}