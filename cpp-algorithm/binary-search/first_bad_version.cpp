// The API isBadVersion is defined for you.
// bool isBadVersion(int version);

typedef long long ll;

class Solution {
public:
    int firstBadVersion(int n) {
        ll start = 1;
        ll end = n;
        int answer = 0;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (isBadVersion(mid)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }
};