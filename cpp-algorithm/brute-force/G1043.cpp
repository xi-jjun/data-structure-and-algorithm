#include <iostream>
#include <set>
#include <vector>

using namespace std;

int N, M, T;
set<int> know_truth;
int party_info[51][52];

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int know, person;
    cin >> N >> M >> T;
    for (int i = 0; i < T; i++) {
        cin >> know;
        know_truth.insert(know);
    }

    for (int i = 0; i < M; i++) {
        bool existed_know = false;
        cin >> party_info[i][0];
        for (int j = 1; j <= party_info[i][0]; j++) {
            cin >> party_info[i][j];
            if (know_truth.find(party_info[i][j]) != know_truth.end()) {
                existed_know = true;
            }
        }
        
        if (existed_know) {
            for (int j = 1; j <= party_info[i][0]; j++) {
                know_truth.insert(party_info[i][j]);
            }
        }
    }
}

void solution() {
    int answer = 0;
    for (int stage = 0; stage < M; stage++) {
        for (int i = 0; i < M; i++) {
            bool existed_know = false;

            for (int j = 1; j <= party_info[i][0]; j++) {
                if (know_truth.find(party_info[i][j]) != know_truth.end()) {
                    existed_know = true;
                }
            }
            
            if (existed_know) {
                for (int j = 1; j <= party_info[i][0]; j++) {
                    know_truth.insert(party_info[i][j]);
                }
            } 
        }
    }

    for (int i = 0; i < M; i++) {
        bool existed_know = false;

        for (int j = 1; j <= party_info[i][0]; j++) {
            if (know_truth.find(party_info[i][j]) != know_truth.end()) {
                existed_know = true;
            }
        }
        
        if (!existed_know) {
            answer++;
        } 
    }

    cout << answer;
}

int main() {
    input();
    solution();
    return 0;
}