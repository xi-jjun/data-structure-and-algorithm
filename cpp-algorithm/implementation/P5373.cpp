#include <iostream>
#include <cstring>
#include <string>
#include <map>
#include <algorithm>
#include <vector>

#define YELLOW 'y'
#define BLUE 'b'
#define WHITE 'w'
#define RED 'r'
#define GREEN 'g'
#define ORANGE 'o'

using namespace std;

struct Nums {
    int n1, n2, n3;
};

const pair<int, int> loc[] = {
    {-1, -1}, // trash
    {1, 1}, // loc[1] = (1, 1) : 1번자리의 좌표는 (1, 1)
    {1, 2}, {1, 3}, {2, 1}, {2, 2}, {2, 3}, {3, 1}, {3, 2}, {3, 3}
};
// [center][face] = {number pads...}
map<pair<char, char>, Nums> matchers;

// [center] = {c_left, c_down, c_right, c_up}
map<char, vector<char>> faces;

int tc;

char FRONT[4][4]; // red
char BACK[4][4]; // orange
char RIGHT[4][4]; // blue
char LEFT[4][4]; // green
char UPSIDE[4][4]; // white
char BOTTOM[4][4]; // yellow

void fill_arr(char arr[][4], char content) {
    for (int i = 1; i <= 3; i++) {
        for (int j = 1; j <= 3; j++) {
            arr[i][j] = content;
        }
    }
}

void show(char arr[][4]) {
    for (int i = 1; i <= 3; i++) {
        for (int j = 1; j <= 3; j++) {
            cout << arr[i][j];
        }
        cout <<"\n";
    }
}

void init_cube() {
    fill_arr(FRONT, RED);
    fill_arr(BACK, ORANGE);
    fill_arr(RIGHT, BLUE);
    fill_arr(LEFT, GREEN);
    fill_arr(UPSIDE, WHITE);
    fill_arr(BOTTOM, YELLOW);
}

void init() {
    init_cube();

    matchers[{'F', 'L'}] = {3, 6, 9};
    matchers[{'F', 'D'}] = {1, 2, 3};
    matchers[{'F', 'R'}] = {1, 4, 7};
    matchers[{'F', 'U'}] = {7, 8, 9};

    matchers[{'R', 'F'}] = {3, 6, 9};
    matchers[{'R', 'D'}] = {3, 6, 9};
    matchers[{'R', 'B'}] = {1, 4, 7};
    matchers[{'R', 'U'}] = {9, 6, 3};

    matchers[{'B', 'R'}] = {3, 6, 9};
    matchers[{'B', 'D'}] = {9, 8, 7};
    matchers[{'B', 'L'}] = {1, 4, 7};
    matchers[{'B', 'U'}] = {3, 2, 1};

    matchers[{'D', 'L'}] = {9, 8, 7};
    matchers[{'D', 'B'}] = {9, 8, 7};
    matchers[{'D', 'R'}] = {7, 8, 9};
    matchers[{'D', 'F'}] = {7, 8, 9};

    matchers[{'L', 'B'}] = {3, 6, 9};
    matchers[{'L', 'D'}] = {7, 4, 1};
    matchers[{'L', 'F'}] = {1, 4, 7};
    matchers[{'L', 'U'}] = {1, 4, 7};

    matchers[{'U', 'L'}] = {1, 2, 3};
    matchers[{'U', 'F'}] = {1, 2, 3};
    matchers[{'U', 'R'}] = {3, 2, 1};
    matchers[{'U', 'B'}] = {3, 2, 1};

    faces['F'] = {'L', 'D', 'R', 'U'};
    faces['R'] = {'F', 'D', 'B', 'U'};
    faces['B'] = {'R', 'D', 'L', 'U'};
    faces['D'] = {'L', 'B', 'R', 'F'};
    faces['L'] = {'B', 'D', 'F', 'U'};
    faces['U'] = {'L', 'F', 'R', 'B'};
}

void input() {
    init();
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> tc;
}

void rotate_center_clockwise(char face[][4]) {
    char new_face[4][4];
    int i = 1;
    for (int col = 1; col <= 3; col++) {
        int j = 1;
        for (int row = 3; row >= 1; row--) {
            new_face[i][j++] = face[row][col];
        }
        i++;
    }
    memcpy(face, new_face, 16 * sizeof(char));
}

void rotate_center_counter_clockwise(char face[][4]) {
    char new_face[4][4];
    int i = 1;
    for (int col = 3; col >= 1; col--) {
        int j = 1;
        for (int row = 1; row <= 3; row++) {
            new_face[i][j++] = face[row][col];
        }
        i++;
    }
    memcpy(face, new_face, 16 * sizeof(char));
}

void rotate_others_counter_clockwise(char center, char l[][4], char d[][4], char r[][4], char u[][4]) {
    int ex_x1, ex_x2, ex_x3, ex_y1, ex_y2, ex_y3;
    char face = faces[center][0]; // left는 미리 temp에 저장.
    // cout << "first face : " << face << "\n";
    int n1 = matchers[{center, face}].n1;
    int n2 = matchers[{center, face}].n2;
    int n3 = matchers[{center, face}].n3;

    tie(ex_x1, ex_y1) = loc[n1]; // n1의 좌표
    tie(ex_x2, ex_y2) = loc[n2];
    tie(ex_x3, ex_y3) = loc[n3];

    char t1 = l[ex_x1][ex_y1];
    char t2 = l[ex_x2][ex_y2];
    char t3 = l[ex_x3][ex_y3];

    // cout << "temp : 1,2,3 = " << t1 << ' ' << t2 << ' ' << t3 << "\n";
    
    for (int i = 3; i >= 1; i--) {
        int x1, x2, x3, y1, y2, y3;
        face = faces[center][i]; 
        // cout << "next face : " << face << "\n";
        n1 = matchers[{center, face}].n1;
        n2 = matchers[{center, face}].n2;
        n3 = matchers[{center, face}].n3;

        tie(x1, y1) = loc[n1];
        tie(x2, y2) = loc[n2];
        tie(x3, y3) = loc[n3];
        // cout << "numberes : " << n1 << ' ' << n2 << ' ' << n3 << "\n";

        if (i == 3) {
            l[ex_x1][ex_y1] = u[x3][y3];
            l[ex_x2][ex_y2] = u[x2][y2];
            l[ex_x3][ex_y3] = u[x1][y1];
        } else if (i == 2) {
            u[ex_x1][ex_y1] = r[x1][y1];
            u[ex_x2][ex_y2] = r[x2][y2];
            u[ex_x3][ex_y3] = r[x3][y3];
        } else if (i == 1) {
            r[ex_x1][ex_y1] = d[x3][y3];
            r[ex_x2][ex_y2] = d[x2][y2];
            r[ex_x3][ex_y3] = d[x1][y1];
        }
        ex_x1 = x1; ex_x2 = x2; ex_x3 = x3; ex_y1 = y1; ex_y2 = y2; ex_y3 = y3;
    }

    d[ex_x1][ex_y1] = t1;
    d[ex_x2][ex_y2] = t2;
    d[ex_x3][ex_y3] = t3;
}

void rotate_others_clockwise(char center, char l[][4], char d[][4], char r[][4], char u[][4]) {
    // cout << "\ntemp left\n";
    // show(l);
    // cout << "\ntemp left end\n";
    int ex_x1, ex_x2, ex_x3, ex_y1, ex_y2, ex_y3;
    char face = faces[center][0]; // left는 미리 temp에 저장.
    // cout << "face : " << face << "\n";
    int n1 = matchers[{center, face}].n1;
    int n2 = matchers[{center, face}].n2;
    int n3 = matchers[{center, face}].n3;

    tie(ex_x1, ex_y1) = loc[n1]; // n1의 좌표
    tie(ex_x2, ex_y2) = loc[n2];
    tie(ex_x3, ex_y3) = loc[n3];
    // cout << "ex : x1, x2, x3, y1, y2, y3 = " << ex_x1 << ' ' << ex_x2 << ' ' << ex_x3 << ' ' << ex_y1 << ' ' << ex_y2 << ' ' << ex_y3 << "\n";

    char t1 = l[ex_x1][ex_y1];
    char t2 = l[ex_x2][ex_y2];
    char t3 = l[ex_x3][ex_y3];
    // cout << "temp : 1,2,3 = " << t1 << ' ' << t2 << ' ' << t3 << "\n";
    
    for (int i = 1; i < faces[center].size(); i++) {
        int x1, x2, x3, y1, y2, y3;
        face = faces[center][i]; // i=0 : Left, i=1 : Down...
        n1 = matchers[{center, face}].n1;
        n2 = matchers[{center, face}].n2;
        n3 = matchers[{center, face}].n3;

        tie(x1, y1) = loc[n1];
        tie(x2, y2) = loc[n2];
        tie(x3, y3) = loc[n3];

        if (i == 1) {
            l[ex_x1][ex_y1] = d[x1][y1];
            l[ex_x2][ex_y2] = d[x2][y2];
            l[ex_x3][ex_y3] = d[x3][y3];
        } else if (i == 2) {
            d[ex_x1][ex_y1] = r[x3][y3];
            d[ex_x2][ex_y2] = r[x2][y2];
            d[ex_x3][ex_y3] = r[x1][y1];
        } else if (i == 3) {
            r[ex_x1][ex_y1] = u[x1][y1];
            r[ex_x2][ex_y2] = u[x2][y2];
            r[ex_x3][ex_y3] = u[x3][y3];
        }
        ex_x1 = x1; ex_x2 = x2; ex_x3 = x3; ex_y1 = y1; ex_y2 = y2; ex_y3 = y3;
    }

    u[ex_x1][ex_y1] = t3;
    u[ex_x2][ex_y2] = t2;
    u[ex_x3][ex_y3] = t1;
}

void process_cmd(string command) {
    char cmd = command[0];
    char dir = command[1];

    if (cmd == 'F') {
        if (dir == '-') {
            rotate_center_counter_clockwise(FRONT);
            rotate_others_counter_clockwise(cmd, LEFT, BOTTOM, RIGHT, UPSIDE);
        } else {
            rotate_center_clockwise(FRONT);
            rotate_others_clockwise(cmd, LEFT, BOTTOM, RIGHT, UPSIDE);
        }
    } else if (cmd == 'D') { // bottom
        if (dir == '-') {
            rotate_center_counter_clockwise(BOTTOM);
            rotate_others_counter_clockwise(cmd, LEFT, BACK, RIGHT, FRONT);
        } else {
            rotate_center_clockwise(BOTTOM);
            rotate_others_clockwise(cmd, LEFT, BACK, RIGHT, FRONT);
        }
    } else if (cmd == 'U') {
        if (dir == '-') {
            rotate_center_counter_clockwise(UPSIDE);
            rotate_others_counter_clockwise(cmd, LEFT, FRONT, RIGHT, BACK);
        } else {
            rotate_center_clockwise(UPSIDE);
            rotate_others_clockwise(cmd, LEFT, FRONT, RIGHT, BACK);
        }
        
    } else if (cmd == 'R') {
        if (dir == '-') {
            rotate_center_counter_clockwise(RIGHT);
            rotate_others_counter_clockwise(cmd, FRONT, BOTTOM, BACK, UPSIDE);
        } else {
            rotate_center_clockwise(RIGHT);
            rotate_others_clockwise(cmd, FRONT, BOTTOM, BACK, UPSIDE);
        }

    } else if (cmd == 'L') {
        if (dir == '-') {
            rotate_center_counter_clockwise(LEFT);
            rotate_others_counter_clockwise(cmd, BACK, BOTTOM, FRONT, UPSIDE);
        } else {
            rotate_center_clockwise(LEFT);
            rotate_others_clockwise(cmd, BACK, BOTTOM, FRONT, UPSIDE);
        }

    } else if (cmd == 'B') { // back
        if (dir == '-') {
            rotate_center_counter_clockwise(BACK);
            rotate_others_counter_clockwise(cmd, RIGHT, BOTTOM, LEFT, UPSIDE);
        } else {
            rotate_center_clockwise(BACK);
            rotate_others_clockwise(cmd, RIGHT, BOTTOM, LEFT, UPSIDE);
        }
    }
}


void solution() {
    int n;
    string cmd;

    while (tc--) {
        init_cube();
        cin >> n;
        while (n--) {
            cin >> cmd;
            process_cmd(cmd);
            // cout << "\nprocess result\n";
            // cout << "1. UPSIDE\n";
            // show(UPSIDE);

            // cout << "2. BACK\n";
            // show(BACK);

            // cout << "3. LEFT\n";
            // show(LEFT);

            // cout << "4. RIGHT\n";
            // show(RIGHT);

            // cout << "5. BOTTOM\n";
            // show(BOTTOM);

            // cout << "6. FRONT\n";
            // show(FRONT);
            // cout << "\n";
        }

        show(UPSIDE);
    }
}

int main() {
    input();
    solution();
    return 0;
}