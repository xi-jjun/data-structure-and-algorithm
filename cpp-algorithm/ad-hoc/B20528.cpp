#include <iostream>
#include <string>

using namespace std;

int N;
string ex, now;
bool ok = true;

void input() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N >> ex;

    for (int i = 1; i < N; i++) {
        cin >> now;
        if (ex[0] != now[0]) {
            ok = false;
            break;
        }
    } 
    
    char answer = ok ? '1' : '0';
    cout << answer;
}

int main() {
    input();
    return 0;
}