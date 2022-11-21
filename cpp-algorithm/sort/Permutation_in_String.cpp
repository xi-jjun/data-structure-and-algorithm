class Solution {
public:
    static bool match(int cnt1[], int cnt2[]) {
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) return false;
        }

        return true;
    }

    bool checkInclusion(string s1, string s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int s1_cnt[26], s2_cnt[26];
        memset(s1_cnt, 0, sizeof(s1_cnt));
        
        for (char c : s1) s1_cnt[c - 'a']++;

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int s2_cnt[26];
            memset(s2_cnt, 0, sizeof(s2_cnt));

            for (int j = i; j < i + s1.length(); j++) {
                s2_cnt[s2[j] - 'a']++;
            }

            if (match(s1_cnt, s2_cnt)) {
                return true;
            }
        }

        return false;
    }
};