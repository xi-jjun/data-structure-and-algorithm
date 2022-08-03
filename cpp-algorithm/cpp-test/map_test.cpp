#include <iostream>
#include <map>
#include <vector>

using namespace std;

void map_char_type_text() {
/*
	map<char, pair<char, char>> tree;
	map['A'] = {'B', 'C'};
	cout << "\nmap char type test\n";
	for (auto node : tree) {
		cout << node.first << "\n";
		cout << node.second << "\n";
	}
// fail
*/
}

int main() {
	map<int, vector<int>> graph;
	graph[1].push_back(123);
	graph[1].push_back(100);
	graph[1].push_back(23);
	graph[354].push_back(90);
	graph[54].push_back(20);
	graph[54].push_back(30);
	graph[4].push_back(-83);
	graph[234].push_back(-1);
	for (auto next_nodes : graph) { // next_nodes : map<int, vector<int>> 1개
		cout << "\nthis no." << next_nodes.first << " => ";
		for (auto node : next_nodes.second) { 
			cout << node << " ";
		}
	}
	
	cout << "\nmap test : 추가안한 key값에 대한 value는 어떻게 되는가?\n";
	map<int, int> test;
	test[1] = 123;
	int a = test[99];
	cout << test[1] << " " << a;
	cout << "\n정답 : 0이 된다\n";

	map_char_type_text();

	return 0;
}
