#include <string>
#include <iostream>

using namespace std;

int main () {
    string str = "This is me";
    int idx = str.find("is");
    cout << "index of 'is' : " << idx << "\n";

    int idx2 = str.find("is", 3); // str의 3번째 인덱스 이후의 "is"를 찾아주세요
    cout << "index of 'is' start of 3 : " << idx2 << "\n";

    int no_existed_idx = str.find("ABC");
    cout << "index of not existed : " << no_existed_idx << "\n";

    return 0;
}