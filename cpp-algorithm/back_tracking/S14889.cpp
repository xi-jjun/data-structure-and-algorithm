#include <iostream>
#include <vector>
#include <cstring>
#include <cmath>

#define TEAM_A true
#define TEAM_B false
#define MAX_ 100000000

using namespace std;

int N, score_A, score_B, answer = MAX_;
int team_A[11], team_B[11];
int S[21][21], score_member[2];
bool is_teamed[21], is_scored[21];

int min(int a, int b) { return a > b ? b : a; }

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> S[i][j];
        }
    }
    memset(is_teamed, false, sizeof(is_teamed));
}

void make_team_B() {
    int idx = 0;
    for (int person = 0; person < N; person++) {
        if (!is_teamed[person]) team_B[idx++] = person;
    }
}

void get_score(bool team_type, int depth) {
    if (depth == 2) {
        int a = score_member[0];
        int b = score_member[1];
        if (team_type) score_A += S[a][b];
        else score_B += S[a][b];

        return;
    }

    for (int i = 0; i < N / 2; i++) {
        if (!is_scored[i]) {
            is_scored[i] = true;
            score_member[depth] = team_type ? team_A[i] : team_B[i];
            get_score(team_type, depth + 1);
            is_scored[i] = false;
        }
    }    
}

void count_score() {
    memset(is_scored, false, sizeof(is_scored));
    get_score(TEAM_A, 0);
    get_score(TEAM_B, 0);
}

void make_team(int depth, int at) {
    if (depth == N / 2) {
        score_A = 0;
        score_B = 0;
        make_team_B();

        count_score();
        
        answer = min(answer, abs(score_A - score_B));

        return;
    }

    for (int person = at; person < N; person++) {
        if (!is_teamed[person]) {
            is_teamed[person] = true;
            team_A[depth] = person;
            make_team(depth + 1, person + 1);
            is_teamed[person] = false;
        }
    }
}

int main() {
    input();
    make_team(0, 0);
    cout << answer;
    return 0;
}
