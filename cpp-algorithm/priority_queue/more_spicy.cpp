#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
    priority_queue<int, vector<int>, greater<int>> pq(scoville.begin(), scoville.end());
    while (!pq.empty()) {
        if (pq.size() == 1 && pq.top() < K) return -1;
        else if (pq.size() == 1) return answer;
        else if (pq.size() >= 2 && pq.top() >= K) return answer;
        else {
            int first = pq.top();
            pq.pop();
            int second = pq.top();
            pq.pop();
            pq.push(first + second * 2);
            ++answer;
        }
        
    }
    return answer;
}
