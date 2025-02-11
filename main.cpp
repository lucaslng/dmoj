#include <iostream>
#include <bits/stdc++.h>
using namespace std;
int main() {

	ios::sync_with_stdio(0);
	cin.tie(NULL);

	int N, M, A, B, x, y;

	cin >> N >> M >> A >> B;
	
	vector<int> graph[5000];

	for (int i=0;i<M;i++) {
		cin >> x >> y;
		graph[x].push_back(y);
		graph[y].push_back(x);
	}

	cout << "hi" << endl;

	return 0;
}