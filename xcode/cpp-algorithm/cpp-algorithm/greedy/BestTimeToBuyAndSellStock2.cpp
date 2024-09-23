//
//  BestTimeToBuyAndSellStock2.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=problem-list-v2&envId=greedy
//  title : 122. Best Time to Buy and Sell Stock II
//  type : greedy
//  difficulty : Medium
//
//  == solution info ==
//  답은 안보고 품. 현재 기준으로 다음날 보다 싼 가격에 살 수 있다면, 무조건 사는게 이득.
//  왜냐하면 당일날 사서 팔 수도 있기 때문.
//
//  Created by 김재준 on 9/24/24.
//

#include <vector>

using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int day = 1;
        int currPrice = 0;
        bool hasStock = false;
        int answer = 0;

        while (day != prices.size() + 1) {
            // 마지막날 더 이상 팔 주식이 없을 경우 정답 반환
            if (day == prices.size() && !hasStock) return answer;
            // 마지막날 보유주식이 있을 경우, 오늘 팔아서 이득이면 팔고, 아니면 안판다
            // => 안팔면 손해? No No. 그 주식을 샀던 떼로 돌아가서 그 날 판다는 가정을 해보면 최고의 수익을 내는 시나리오임. 그래서 주식으로 손해를 본다는 선택지는 존재하지 않음.
            if (day == prices.size() && hasStock && currPrice <= prices[day - 1]) {
                answer += prices[day - 1] - currPrice;
                return answer;
            }

            int todayPrice = prices[day - 1];
            int nextdayPrice = prices[day];

            if (hasStock) {
                if (currPrice <= todayPrice) {
                    hasStock = false;
                    answer += todayPrice - currPrice;

                    // 오늘 판 주식을 한번 더 살지 판단하는 로직
                    if (todayPrice < nextdayPrice) {
                        hasStock = true;
                        currPrice = todayPrice;
                    }
                }
            } else {
                if (todayPrice < nextdayPrice) {
                    hasStock = true;
                    currPrice = todayPrice;
                }
            }
            day++;
        }

        return answer;
    }
};
