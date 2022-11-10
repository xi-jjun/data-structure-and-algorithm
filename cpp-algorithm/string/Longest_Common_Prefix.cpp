#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    int min(int a, int b) { return a > b ? b : a; }

    bool has_same_char(vector<string> strs, int idx) {
        char std_char = strs[0][idx];
        for (string word : strs) {
            if (word[idx] != std_char) return false;
        }

        return true;
    }

    int get_min_str_len(vector<string> strs) {
        int ret = 987654321;
        for (string str : strs) {
            ret = min(ret, str.length());
        }
        return ret;
    }

    string longestCommonPrefix(vector<string>& strs) {
        int times = get_min_str_len(strs);
        int idx = 0;
        for (int i = 0; i < times; i++) {
            if (!has_same_char(strs, i)) break;
            idx++;
        }

        return strs[0].substr(0, idx);
    }
};