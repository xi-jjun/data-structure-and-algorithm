// https://luv-n-interest.tistory.com/1253
#include <iostream>
#include <algorithm>

using namespace std;

int N;
int A[1001], asc[1001], desc[1001];

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for (int i = 0; i < N; i++) cin >> A[i];
}

void make_asc() {
    for (int i = 0; i < N; i++) {
        asc[i] = 1;
        int standard = A[i];
        for (int j = 0; j < i; j++) {
            if (standard > A[j]) asc[i] = max(asc[i], asc[j] + 1);
        }
    }
}

void make_desc() {
    for (int i = N - 1; i >= 0; i--) {
        desc[i] = 1;
        int standard = A[i];
        for (int j = N - 1; j > i; j--) {
            if (standard > A[j]) desc[i] = max(desc[i], desc[j] + 1);
        }
    }
}

int get_answer() {
    int answer = 0;
    for (int i = 0; i < N; i++) {
        answer = max(answer, desc[i] + asc[i] - 1);
        // cout << answer << "\n";
    }
    return answer;
}

void solution() {
    // fill(asc, asc + 1001, 1);
    // for (int i = 1; i <= N; i++) cout << asc[i] << ' ';
    // cout << "\n";
    // fill(desc, desc + 1001, 1);
    // for (int i = 1; i <= N; i++) cout << desc[i] << ' ';
    make_asc(); 
    make_desc();

    cout << get_answer();
}

int main() {
    input();
    solution();

    return 0;
}