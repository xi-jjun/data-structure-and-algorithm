//
//  GasStation.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/gas-station/description/?envType=problem-list-v2&envId=greedy
//  title : 134. Gas Station
//  type : greedy
//  difficulty : Medium
//
//  == solution info ==
//  답은 안보고 품.처음에 brute force로 풀다가 시간초과나서 틀림.
//  이후 풀이에 대한 비효율적인 부분을 확인하여 개선.
//  컨셉 : 모든 초기비용을 다 더해서, 음수라면 return -1, 양수라면 더하면서 마이너스가 안되게끔 하는 최초의 인덱스를 찾으면 됨
//
//  Created by 김재준 on 9/25/24.
//
#include <iostream>

#include <vector>

using namespace std;

class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int N = gas.size();
        vector<int> route_costs(N); // 해당 값을 순서대로 더해서 중간에 0 미만이 안나올 경우, 더하기 시작한 인덱스가 바로 정답
        
        int total_cost = 0;
        for (int i = 0; i < N; i++) {
            route_costs[i] = gas[i] - cost[i];
            total_cost += route_costs[i];
        }
        
        if (total_cost < 0) return -1;
        
        int answer = 0; // 정답
        int step = 0; // +1 증가시킬 변수
        int total_route_cost = 0; // 현재 소요된 비용 총합 (0 미만으로 떨어지면 다시 처음부터 찾아야 함)
        int try_count = 0; // 시도 횟수
        while (try_count != N) {
            int route_cost = route_costs[step % N];
            if (total_route_cost + route_cost < 0) {
                total_route_cost = 0;
                try_count = 0;
                answer = (step + 1) % N;
            } else {
                total_route_cost += route_cost;
            }
            step++;
            try_count++;
        }
        
        return answer;
    }
    
    // 시간초과로 실패한 정답
    int canCompleteCircuitByBruteForce(vector<int>& gas, vector<int>& cost) {
        int N = gas.size();
        
        for(int start_idx = 0; start_idx < N; start_idx++) {
            if (gas[start_idx] < cost[start_idx]) continue;
            
            int curr_idx = start_idx;
            int my_gas = gas[curr_idx] - cost[curr_idx];
            bool ans_flag = true;
            
            for (int step = 1; step <= N; step++) {
                curr_idx = (start_idx + step) % N;
                if (my_gas + gas[curr_idx] < cost[curr_idx]) {
                    ans_flag = false;
                    break;
                } else {
                    my_gas = my_gas + gas[curr_idx] - cost[curr_idx];
                }
            }
            
            if (ans_flag) return start_idx;
        }
        
        return -1;
    }
};

//int main() {
//    Solution sol;
//    
//    vector<int> gas1 = { 1, 2, 3, 4, 5 };
//    vector<int> cost1 = { 3, 4, 5, 1, 2 };
//    int result = sol.canCompleteCircuit(gas1, cost1);
//    cout << result;
//}
