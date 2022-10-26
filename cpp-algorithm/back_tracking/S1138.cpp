#include <iostream>
#include <cstring>

using namespace std;

int N;
int selected[11];
int answer[11];
int input_counts[11];
bool ok = false;
bool used[11];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> input_counts[i];
}

bool valid() {
    int counted_left[11];
    memset(counted_left, 0, sizeof(counted_left));

    for (int i = 1; i <= N; i++) {
        int now_height = selected[i];

        for (int left = 1; left < i; left++) {
            if (selected[left] > now_height) { 
                counted_left[now_height]++;
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        if (input_counts[i] != counted_left[i]) return false;
    }

    return true;
}

void back_tracking(int depth) {
    if (ok) return;
    else if (depth == N + 1) {
        // for (int i = 1; i <= N; i++) cout << selected[i] << ' ';
        // cout << "\n";
        if (valid()) {
            ok = true;
            memcpy(answer, selected, 11 * sizeof(int));
            return;
        }
        return;
    }

    for (int number = 1; number <= N; number++) {
        if (!used[number]) {
            used[number] = true;
            selected[depth] = number;
            back_tracking(depth + 1);
            used[number] = false;
        }
    }
}

void solution() {
    memset(used, false, sizeof(used));
    back_tracking(1);
    
    for (int i = 1; i <= N; i++) {
        cout << answer[i] << ' ';
    }
}

int main() {
    input();
    solution();
    return 0;
}