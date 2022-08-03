#include <iostream>
#include <queue>
#include <set>
#include <map>

using namespace std;

int edge_cnt = 0;
bool end_flag = false;

pair<set<int>, map<int, vector<int>>> get_input() {
	set<int> vertex;
	map<int, vector<int>> tree;
	while (1) {
		int u, v;
		cin >> u >> v;
		if (u == 0 && v == 0) return {vertex, tree};
		vertex.insert(u);
		vertex.insert(v);
		if (u == -1 && v == -1) { 
			end_flag = true;
			return {vertex, tree};
		}
		tree[u].push_back(v);
		edge_cnt++;
	}
}

map<int, int> make_existed_cnt(map<int, vector<int>>& tree) {
	map<int, int> visit_cnt;
	for (auto nodes : tree) {
		for (auto node : nodes.second) {
			visit_cnt[node] = 1; // check node is existed
		}
	}
	return visit_cnt;
}

int find_root(map<int, int> checker, map<int, vector<int>> tree) {
	for (auto element : tree) {
		int vertex = element.first;
		if (!checker[vertex]) return vertex;
	}

	return -1;
}

bool check_cycle_existed(int root, map<int, int>& visit_cnt, map<int, vector<int>> tree) {
	queue<int> q;
	q.push(root);
	while (!q.empty()) {
		int now = q.front();
		q.pop();

		for (int i = 0; i < tree[now].size(); i++) {
			int next = tree[now][i];
			if (visit_cnt[next] == 2) return true; // cycle is existed
			visit_cnt[next]++; // check visit
			q.push(next);
		}
	}

	return false;
}

bool check_all_visited(map<int, int> visit_cnt, map<int, vector<int>> tree) {
	for (auto nodes : tree) {
		for (auto node : nodes.second) {
			if (visit_cnt[node] != 2) return false;
		}
	}
	return true;
}

bool valid_cycle_and_visited(map<int, vector<int>>& tree) {
	map<int, int> visit_cnt = make_existed_cnt(tree);
	
	int root = find_root(visit_cnt, tree);
	if (root == -1) return false;

	if (check_cycle_existed(root, visit_cnt, tree)) return false;

	return check_all_visited(visit_cnt, tree);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int case_cnt = 1;
	pair<set<int>, map<int, vector<int>>> data;
	while (1) {
		edge_cnt = 0;
		data = get_input();
		set<int> vertex = data.first;
		map<int, vector<int>> tree = data.second;
		
		if (end_flag) break;
		if (vertex.size() == 0) cout << "Case " << case_cnt << " is a tree.\n";
		else if (vertex.size() != edge_cnt + 1) cout << "Case " << case_cnt << " is not a tree.\n";
		else {
			if (valid_cycle_and_visited(tree)) {
				cout << "Case " << case_cnt << " is a tree.\n";
			} else {
				cout << "Case " << case_cnt << " is not a tree.\n";
			}
		}
		case_cnt++;
	}

	return 0;
}
