#include <set>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

struct my_struct {
    int a;
    string name;
    string serial_number;

    my_struct(int _a, string _name, string _serial_number) : a(_a), name(_name), serial_number(_serial_number) {};

    bool operator == (const my_struct& rhs) {
        return a == rhs.a && name == rhs.name && serial_number == rhs.serial_number;
    }

    bool operator < (const my_struct& rhs) const {
        return a < rhs.a;
    }
};

int main() {
    // set<my_struct> my_set;
    // my_struct ins = my_struct(1, "KJJ2", "34KN34");
    // my_struct ins1 = my_struct(1, "KJJ1", "34KN34");
    // my_struct ins2 = my_struct(1, "KJJ3", "34KN34");
    // cout << ins.a << "\n";
    // cout << ins.name << "\n";
    // cout << ins.serial_number << "\n";
    // my_set.insert(ins);
    // my_set.insert(ins2);
    // my_set.insert(ins1);
    // // my_set.insert(my_struct(1, "KJJ", "4kNi34"));
    // // my_set.insert(my_struct(1, "KJJ", "4kNi34"));
    // // my_set.insert(my_struct(1, "KJJ", "4kNi34"));
    // // my_set.insert(my_struct(1, "KJJ", "4kNi34"));
    // // my_set.insert(my_struct(1, "KJ2J", "4kNi3d4"));

    // cout << my_set.size();

    set<vector<string>> my_str_set;
    vector<string> v1;
    v1.push_back("Hello");
    v1.push_back("Hello2");
    v1.push_back("Hello4");

    vector<string> v2;
    v2.push_back("Hello");
    v2.push_back("Hello2");
    v2.push_back("Hello4");
    
    my_str_set(v1);
    my_str_set(v2);
    return 0;
}