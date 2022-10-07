#include <iostream>
#include <vector>
using namespace std;
int n;
vector<vector<int>> cities;
vector<bool> visited;
vector<vector<bool>> g;
int mini = 0;
int maxi = 2147483647;
void dfs(int d,int i){
	visited[d] = true;
	for(int j = 0; j < n ; j++){
		bool enter = false;
		if(i == 1){
			enter = g[j][d] && !visited[j];
		}else{
			enter = g[d][j] && !visited[j];
		}
		if(enter){
			dfs(j,i);
		}
	}
}
bool all_visited(){
	for(int i = 0 ; i < n; i++){
		if(!visited[i]){
			return false;
		}
	}
	return true;
}
int main()
{
	cin >> n;
	cities.resize(n);
	visited.resize(n);
	g.resize(n);
	fill(visited.begin(),visited.end(),false);
	for (int i = 0; i < n; i++)
	{
		vector<int> v(n);
		vector<bool> b(n);
		for (int j = 0; j < n; j++)
		{
			int k;
			cin >> k;
			v[j] = k;
			b[j] = false;
		}
		g[i] = b;
		cities[i] = v;
	}
	while(mini<maxi){
		int mid = (mini+maxi)/2;
		for(int i = 0; i < n; i++){
			for(int j = 0 ; j < n; j ++){
				g[i][j] = cities[i][j] <= mid;
			}
		}
		fill(visited.begin(),visited.end(),false);
		dfs(0,0);
		bool b = false;
		if(all_visited()){
			fill(visited.begin(),visited.end(),false);
			dfs(0,1);
			if(!all_visited()){
				b = true;
			}
		}else{
			b =true;
		}
		if(!b){
			maxi = mid;
		}else{
			mini = mid + 1;
		}
	}
	cout<<mini<<endl;
	return 0;
}