#include <iostream>
#include <vector>
#include <numeric>

using namespace std;

int M, N;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> M >> N;
}

bool valid(int number) {
    return M <= number && number <= N;
}

void solution() {
    int number = 1;
    vector<int> result;
    while (true) {
        int double_number = number * number;
        if (valid(double_number)) {
            result.push_back(double_number);
        } else if (double_number > N) break;
        number++;
    }

    if (result.size() == 0) {
        cout << "-1";
        return;
    }

    cout << accumulate(result.begin(), result.end(), 0) << "\n";
    cout << result[0];
}

int main() {
    input();
    solution();
    return 0;
}