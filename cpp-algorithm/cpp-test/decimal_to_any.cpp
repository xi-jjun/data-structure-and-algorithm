#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

string get_place_element(int number, int base) {
    if (base < 10 || number < 10) return to_string(number);
    string ret(1, 'A' + number - 10);
    return ret;
}

string dec_to_binary(int dec) {
    string ret;
    while (dec != 0) {
        ret.append(to_string(dec % 2));
        dec /= 2;
    }
    reverse(ret.begin(), ret.end());

    return ret;
}

string dec_to_triad(int dec) {
    string ret;
    while (dec != 0) {
        ret.append(to_string(dec % 3));
        dec /= 3;
    }
    reverse(ret.begin(), ret.end());

    return ret;
}

string dec_to_any(int dec, int base) {
    string ret;
    while (dec != 0) {
        ret.append(get_place_element(dec % base, base));
        dec /= base;
    }
    reverse(ret.begin(), ret.end());

    return ret;
}

int main() {
    const int std_number = 124135;
    cout << "binary converted : " << dec_to_binary(std_number) << "\n";
    cout << "triad converted : " << dec_to_triad(std_number) << "\n";

    for (int base = 3; base <= 16; base++) {
        cout << "base[" << base << "] converted : " << dec_to_any(std_number, base) << "\n";
    }

    return 0;
}