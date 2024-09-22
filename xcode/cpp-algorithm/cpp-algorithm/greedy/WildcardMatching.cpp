//
//  WildcardMatching.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/wildcard-matching/?envType=problem-list-v2&envId=greedy
//  title : 44. Wildcard Matching
//  type : greedy
//  difficulty : Hard
//
//  == solution info ==
//  답지 보고 품
//
//  Created by 김재준 on 9/22/24.
//

#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <cstring> // for memset

using namespace std;

class Solution {
public:
    bool dp[2001][2001];
    
    bool isMatch(string s, string p) {
        memset(dp, false, sizeof(dp));
        dp[0][0] = true;
        
        for (int pIdx = 1; pIdx <= p.size(); pIdx++) {
            if (p[pIdx - 1] == '*') {
                dp[pIdx][0] = dp[pIdx - 1][0];
            } else { break; }
        }
        
        for (int pIdx = 1; pIdx <= p.size(); pIdx++) {
            for (int sIdx = 1; sIdx <= s.size(); sIdx++) {
                if (p[pIdx - 1] == s[sIdx - 1] || p[pIdx - 1] == '?') {
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1];
                } else if (p[pIdx - 1] == '*') {
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx] || dp[pIdx][sIdx - 1];
                } else {
                    dp[pIdx][sIdx] = false;
                }
            }
        }
        
        return dp[p.size()][s.size()];
    }
};

// Test case
class ExampleData {
public:
    pair<string, string> inputs; // S, P
    bool output;
    
    ExampleData(string s, string p, bool expectedAnswer) {
        inputs.first = s;
        inputs.second = p;
        output = expectedAnswer;
    }
};

// Main
int main() {
    Solution sol;
    
    vector<ExampleData> examples;
    examples.push_back(ExampleData("abcdef", "abcdef", true));
    examples.push_back(ExampleData("abcdef", "ab*def", true));
    examples.push_back(ExampleData("abcdef", "a?c?ef", true));
    examples.push_back(ExampleData("abcdef", "a?c?e", false));
    examples.push_back(ExampleData("abcdef", "a*c*ef", true));
    examples.push_back(ExampleData("abcdef", "a*c?ef", true));
    examples.push_back(ExampleData("abcdef", "a?*?ef", true));
    examples.push_back(ExampleData("abcdef", "a*?c?ef", true));
    
    for (const auto& exampleData : examples) {
        string s = exampleData.inputs.first;
        string p = exampleData.inputs.second;
        bool expectedAnswer = exampleData.output;
        
        bool result = sol.isMatch(s, p);
        if (result != expectedAnswer) {
            cout << "WRONG ANSWER\n";
        } else { cout << "ANSWER CORRECTED!\n"; }
    }
}
