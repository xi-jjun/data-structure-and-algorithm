//
//  SameTree.cpp
//  cpp-algorithm
//
//  == Problem Info ==
//  leetcode
//  problem link : https://leetcode.com/problems/same-tree/?envType=problem-list-v2&envId=breadth-first-search
//  title : 100. Same Tree
//  type : BFS
//  difficulty : Easy
//
//  == solution info ==
//  BFS 컨셉 대충 보고, C++ 포인터, queue 사용법 검색하여 확인
//
//  Created by 김재준 on 9/24/24.
//

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
};

#include <queue>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> p_arr;
    vector<int> q_arr;
    int MAX_VALUE = 99999999; // 필요없는 값임을 표시

    bool isSameTree(TreeNode* p, TreeNode* q) {
        bfs(p, true);
        bfs(q, false);

        return p_arr == q_arr;
    }

    void bfs(TreeNode* rootNode, bool type) {
        if (rootNode == nullptr) return;

        queue<TreeNode*> q;
        q.push(rootNode);
        type ? p_arr.push_back(rootNode->val) : q_arr.push_back(rootNode->val);

        while (!q.empty()) {
            TreeNode* now = q.front();
            q.pop();
            TreeNode* left = now->left;
            TreeNode* right = now->right;

            if (left != nullptr) {
                q.push(left);
                type ? p_arr.push_back(left->val) : q_arr.push_back(left->val);
            } else {
                type ? p_arr.push_back(MAX_VALUE) : q_arr.push_back(MAX_VALUE);
            }

            if (right != nullptr) {
                q.push(right);
                type ? p_arr.push_back(right->val) : q_arr.push_back(right->val);
            } else {
                type ? p_arr.push_back(MAX_VALUE) : q_arr.push_back(MAX_VALUE);
            }
        }
    }
};
