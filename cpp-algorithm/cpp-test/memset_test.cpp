#include <iostream>
#include <cstring>

using namespace std;


int main() {
	int comp[10][20];
	//memset(comp, -23, sizeof(comp[0][0] * 10 * 20));
	memset(comp, 0x3f, sizeof(comp));
	
	//for (int i = 0; i < 10; i++) memset(comp[i], -1, sizeof(comp[i]));

	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 20; j++) {
			cout << comp[i][j] << ' ';
		}
		cout << "\n";
	}
}
