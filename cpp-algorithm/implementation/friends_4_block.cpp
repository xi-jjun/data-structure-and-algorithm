#include <string>
#include <vector>
#include <cstring>

#define EMPTY ' '

using namespace std;

int M, N, erased_cnt = 0;
vector<string> map;
bool erased[32][32];
pair<int, int> d[4] = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

void init(int m, int n, vector<string> board) {
    M = m;
    N = n;
    map = board;
}

bool is_erasable(int x, int y) {
    return map[x][y] == map[x][y + 1] && map[x][y + 1] == map[x + 1][y] && map[x][y] == map[x + 1][y + 1];
}

bool checking_erased_and_counting(int x, int y) {
    if (x + 1 >= M || y + 1 >= N) return false;
    
    if (is_erasable(x, y)) {
        for (auto dd : d) {
            int dx = dd.first;
            int dy = dd.second;
            if (!erased[x + dx][y + dy]) erased_cnt++;
            erased[x + dx][y + dy] = true; // check : to be erased
        }
        return true;
    }
    return false;
}

bool find_erased_block_and_checking() {
    memset(erased, false, sizeof(erased));
    bool program_end = true;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if (map[i][j] == EMPTY) continue;
            
            if (checking_erased_and_counting(i, j)) {
                program_end = false;    
            }
        }
    }
    
    return program_end;
}

void erase_block() {
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if (erased[i][j]) map[i][j] = EMPTY;
        }
    }
}

void lowering_block() {
    for (int col = 0; col < N; col++) {
        int save_row = M - 1;
        for (int row = M - 1; row >= 0; row--) {
            if (map[row][col] != EMPTY) map[save_row--][col] = map[row][col];
        }
        
        while (save_row >= 0) {
            map[save_row--][col] = EMPTY;
        }
    }
}

int solution(int m, int n, vector<string> board) {
    init(m, n, board);
    /**
    1. 지워질 block 표시. 없으면 return answer하고 solution 종료
    2. block 제거
    3. 칸 내리기
    */

    while (1) {
        bool is_end = find_erased_block_and_checking();
        if (is_end) break;
        
        erase_block();
        lowering_block();
    }

    return erased_cnt;
}