//
//  JumpGame2.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/jump-game-ii/description/?envType=problem-list-v2&envId=greedy
//  title : 45. Jump Game II
//  type : greedy
//  difficulty : Medium
//
//  == solution info ==
//  답지 보고 품
//
//  Created by 김재준 on 9/23/24.
//

#include <vector>

using namespace std;

class Solution {
public:
    int jump(vector<int>& nums) {
        // [2,3,1,1,4]
        // 1. max = 2, curr = 2, answer = 1
        // 2. max = 4, curr = 4, answer = 2
        // [1, 2, 1, 1, 1]
        // 1. i=0 max = 1, curr = 1, ans = 1
        // 2. max = 3, curr = 3, ans = 2
        // 3. max = 3, curr = 3, ans = 2
        // 4. i=3, max = 4, curr = 4, ans = 3
        // [1,2,3]
        // 1. max = 1, curr = 1, ans = 1
        // 2. i=1, max = 3, curr = 3, ans = 2
        // [7,0,9,6,9,6,1,7,9,0,1,2,9,0,3] : size = 16
        // 1. i=0, max=7, curr=7, ans=1
        // 2. i=1, max=7, curr=7, ans=1
        // 3. i=2, max=11, curr=11, ans=2
        // 3. i=3, max=11, curr=11, ans=2
        // 4. i=4, max=13, curr=13, ans=3
        if (nums.size() <= 1) return 0;
        int maxReach = 0; // 현 위치에서 도달 가능한 최대 거리
        int currentLoc = 0; // 현 위치
        int answer = 0; // 점프 횟수
        
        for (int i = 0; i < nums.size(); i++) {
            maxReach = max(maxReach, i + nums[i]);
            
            // 틀림) 최대 도달 거리가 현 위치보다 멀면, 지금이 바로 jump 해야하는 순간
            // 이유 : 매번 최대 도달 거리 기준으로 계산하면, 잘못된 값이 나옴. 기준이 틀림.
//            if (currentLoc < maxReach) {
//                currentLoc = maxReach;
//                answer++;
//            }
            
            // i : 현재 확인중인 index
            // currentLoc : 다음에 점프 뛰어야할 i의 위치
            // maxReach : 매 순간순간의 최대 도달 가능 거리
            if (i == currentLoc) { // 현 위치까지의 maxReach는 모두 조사 완료. 이제 조사한 내용(maxReach)기반으로 점프를 할 순간임
                // JUMP!!
                currentLoc = maxReach; // 점프 뛰어서 현재 위치를 갱신
                answer++; // 점프 횟수 +1
            }
            
            if (currentLoc >= nums.size() - 1) {
                return answer;
            }
        }

        return answer;
    }

    int max(int a, int b) { return a <= b ? b : a; }
};
