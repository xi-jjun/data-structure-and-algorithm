#include <iostream>
#include <queue>
#include <cmath>

using namespace std;

/*
	custom comparator
	절댓값이 더 작은 값에 우선순위를 부여.
	만약 절댓값이 같다면, 더 큰 수에게 우선순위 부여.
*/
struct cmp {
	bool operator()(int n1, int n2) {
		if (abs(n1) > abs(n2)) {
			return true;
		} else if (abs(n1) == abs(n2)) {
			if (n1 > n2) return true;
			return false;
		} else return false;
	}
};

void custom_cmp() {
	cout << "사용자 지정 비교함수 선언\n";
	priority_queue<int, vector<int>, cmp> my_pq;
	my_pq.push(1);
	my_pq.push(-2);
	my_pq.push(-13);
	my_pq.push(2);
	my_pq.push(12);
	my_pq.push(5);
	my_pq.push(-6);

	while (!my_pq.empty()) {
		cout << my_pq.top() << "\n";
		my_pq.pop();
	}
}


int main() {
	cout << "오름차순 정렬\n";
	priority_queue<int, vector<int>, greater<int> > pq1;
	pq1.push(4);
	pq1.push(1);
	pq1.push(3);
	pq1.push(-4);
	pq1.push(340);
	pq1.push(88);
	pq1.push(-23823);
	while (!pq1.empty()) {
		cout << pq1.top() << "\n";
		pq1.pop();
	}

	custom_cmp();

	return 0;
}
