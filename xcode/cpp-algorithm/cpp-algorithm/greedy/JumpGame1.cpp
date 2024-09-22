//
//  JumpGame1.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/jump-game/description/?envType=problem-list-v2&envId=greedy
//  title : 55. Jump Game
//  type : greedy
//  difficulty : Medium
//
//  == solution info ==
//  답은 안보고 GPT한테 힌트 얻음
//      => 나중에 깨달은 내용 : 도달 못하게되면 결국 어떻게든 못하게 됨. 따라서 최대거리로 도달못할 경우만 봐도 됨.
//
//  Created by 김재준 on 9/23/24.
//

#include <vector>

using namespace std;

class Solution {
public:
    bool canJump(vector<int>& nums) {
        if (nums.size() == 1) return true;
        int maxReach = 0; // 현 위치에서 도달 가능한 최대 거리
        int currentLoc = 0; // 현 위치

        // [3,2,1,0,4]
        // i=0, max=3, curr=3, answer++
        // i=1, max=3, curr=3, answer
        // i=2, max=3, curr=3, answer
        // i=3, max=3, curr=3
        for (int i = 0; i < nums.size(); i++) {
            maxReach = max(maxReach, i + nums[i]);
            
            // i : 현재 확인중인 index
            // currentLoc : 다음에 점프 뛰어야할 i의 위치
            // maxReach : 매 순간순간의 최대 도달 가능 거리
            if (i == currentLoc) { // 현 위치까지의 maxReach는 모두 조사 완료. 이제 조사한 내용(maxReach)기반으로 점프를 할 순간임
                // JUMP!!
                // 최대 도달 가능 거리가 현 위치랑 같을 경우, 더 이상의 전진이 불가능하기에 마지막 인덱스에 도달 불가능
                if (currentLoc == maxReach) return false;
                
                currentLoc = maxReach; // 점프 뛰어서 현재 위치를 갱신
            }
            
            if (currentLoc >= nums.size() - 1) {
                // 마지막 인덱스에 도달하면 true early return
                return true;
            }
        }

        return true;
    }
};
