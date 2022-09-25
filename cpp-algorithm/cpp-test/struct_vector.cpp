#include <vector>
#include <iostream>

using namespace std;

struct Pos {
    bool is_merge;
    vector<int> v;
};

int main() {
    Pos p_arr[4];
    p_arr[0] = {false, vector<int> (2, 2)};
    p_arr[0].v.push_back(124314314);
    for (auto data : p_arr[0].v) cout << data << ' ';
    return 0;
}