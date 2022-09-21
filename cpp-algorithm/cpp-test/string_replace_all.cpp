#include <string>
#include <iostream>

using namespace std;

void replace_all(string &message, string pattern, string replaced) {
    int pos = 0, offset = 0;
    while ((pos = message.find(pattern, offset)) != -1) {
        message.replace(message.begin() + pos, message.begin() + pos + pattern.length(), replaced);
        offset = pos + replaced.length();
    } 
}

int main() {
    string m = "ABSDAGEeABreaABEG";
    cout << "before m : " << m << "\n";
    replace_all(m, "AB", "PPP");
    cout << "after m : " << m << "\n";

    return 0;
}