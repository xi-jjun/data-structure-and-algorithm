//
//  LargestNumber.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/largest-number/?envType=problem-list-v2&envId=greedy
//  title : 179. Largest Number
//  type : greedy
//  difficulty : Medium
//
//  == solution info ==
//  답 안보고 품. 처음에는 10자리 zero padding, 이후에는 10자리 숫자반복, 마지막에는 최소공배수만큼 숫자반복하여 비교해서 해답찾음
//
//  Created by 김재준 on 9/29/24.
//

#include <iostream>

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    static bool compare(int a, int b) {
        string a_str = to_string(a);
        string b_str = to_string(b);
        int a_len = a_str.length();
        int b_len = b_str.length();

        string a_result = convert_str_and_retry_str(a_str, b_len);
        string b_result = convert_str_and_retry_str(b_str, a_len);

        return a_result > b_result;
    }

    string largestNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end(), compare);
        string answer;
        for (auto num : nums) {
            answer.append(to_string(num));
        }

        return answer[0] == '0' ? "0" : answer;
    }

    static string convert_str_and_retry_str(string num_str, int retry_cnt) {
        string ret;
        while (retry_cnt--) ret.append(num_str);

        return ret;
    }
};
