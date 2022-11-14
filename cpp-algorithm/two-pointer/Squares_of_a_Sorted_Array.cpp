class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        vector<int> answer(nums.size(), 0);
        int start = 0;
        int end = nums.size() - 1;

        for (int i = nums.size() - 1; i >= 0; i--) {
            if (abs(nums[start]) > abs(nums[end])) {
                answer[i] = nums[start] * nums[start];
                start++;
            } else {
                answer[i] = nums[end] * nums[end];
                end--;
            }
        }

        return answer;
    }
};