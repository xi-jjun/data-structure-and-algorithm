#include <iostream>
#include <map>
#include <vector>

using namespace std;

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
}
