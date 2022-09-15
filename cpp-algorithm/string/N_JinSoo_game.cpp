#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool tube_speak(int user_seq, int tube_turn) {
    return user_seq == tube_turn - 1;
}

string get_place_element(int number, int base) {
    if (number < 10) return to_string(number);
    string ret(1, 'A' + number - 10);
    return ret;
}

string dec_to_base(int dec, int base) {
    if (dec == 0) return "0";
    
    string ret;
    while (dec != 0) {
        ret.append(get_place_element(dec % base, base));
        dec /= base;
    }
    reverse(ret.begin(), ret.end());
    
    return ret;
}

string solution(int n, int t, int m, int p) {
    string answer;
    int number_seq = 0;
    int user_seq = 0;
    while (t != answer.length()) {
        string now_number = dec_to_base(number_seq++, n);
        
        for (auto speak : now_number) {
            if (tube_speak(user_seq, p)) answer.push_back(speak);
            if (t == answer.length()) break;
            user_seq = (user_seq + 1) % m;
        }
    }
    return answer;
}