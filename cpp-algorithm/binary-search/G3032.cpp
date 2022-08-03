#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int H, break_cnt = 200001, cnt;
vector<int> bot_rock;
vector<int> top_rock;

void get_input() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	cin >> N >> H;
	int height;
	for (int i = 0; i < N; i++) {
		cin >> height;
		if (i % 2 == 0) bot_rock.push_back(height);
		else top_rock.push_back(height);
	}
}

void solution() {
	sort(bot_rock.begin(), bot_rock.end());
	sort(top_rock.begin(), top_rock.end());

	for (int range_h = 1; range_h <= H; range_h++) {
		int bot_break_cnt = bot_rock.size() - (lower_bound(bot_rock.begin(), bot_rock.end(), range_h) - bot_rock.begin());
		int top_break_cnt = top_rock.size() - (lower_bound(top_rock.begin(), top_rock.end(), H - range_h + 1) - top_rock.begin());
		int total_break_cnt = bot_break_cnt + top_break_cnt;

		if (total_break_cnt == break_cnt) {
			cnt++;
		} else if (total_break_cnt < break_cnt) {
			break_cnt = total_break_cnt;
			cnt = 1;
		}
	}

	cout << break_cnt << ' ' << cnt;
}

int main() {
	get_input();
	solution();
	return 0;
}
