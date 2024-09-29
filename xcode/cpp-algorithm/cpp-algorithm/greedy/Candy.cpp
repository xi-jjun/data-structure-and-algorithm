//
//  Candy.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/candy/description/?envType=problem-list-v2&envId=greedy
//  title : 135. Candy
//  type : greedy
//  difficulty : Hard
//
//  == solution info ==
//  답 보고 품. 왼쪽 --> 오른쪽 / 오른쪽 --> 왼쪽으로 순회를 한번씩 하면 되는 것을 몰랐음.
//  이렇게 될 수 있는 이유는 각 조건이 서로에게 상호보완적이기 때문이라고 함...
//
//  Created by 김재준 on 9/29/24.
//

#include <vector>
#include <numeric>

using namespace std;

class Solution {
public:
    int candy(vector<int>& ratings) {
        int N = ratings.size();
        vector<int> candies(N, 1);

        for (int i = 0; i < N - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                candies[i + 1] = candies[i] + 1;
            }
        }
        
        for (int i = N - 1; i >= 1; i--) {
            if (ratings[i - 1] > ratings[i]) {
                candies[i - 1] = max(candies[i] + 1, candies[i - 1]);
            }
        }

        return accumulate(candies.begin(), candies.end(), 0);
    }

    int max(int a, int b) { return a > b ? a : b; }
};
