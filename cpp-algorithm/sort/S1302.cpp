#include <iostream>
#include <algorithm>
#include <map>
#include <string>
#include <vector>

using namespace std;

int N;
string book_name;

void input() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N;
}

bool cmp(pair<int, string> p1, pair<int, string> p2) {
    if (p1.first == p2.first) {
        return p1.second < p2.second;
    }
    return p1.first > p2.first;
}

void solution() {
    map<string, int> sold;

    for (int i = 0; i < N; i++) {
        cin >> book_name;
        sold[book_name]++; 
    }

    vector<pair<int, string>> sold_list;

    auto iter = sold.begin();
    while (iter != sold.end()) {
        sold_list.push_back({iter->second, iter->first});
        iter++;
    }

    sort(sold_list.begin(), sold_list.end(), cmp);

    cout << sold_list[0].second;
}

int main() {
    input();
    solution();
    return 0;
}