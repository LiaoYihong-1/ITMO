#include <iostream>
#include <deque>
#include <vector>
using namespace std;
int n, m;
int vi = 0;
int UNWRITTEN = 0;
vector<vector<int>> students;
vector<int> colors;
void init_all(){
    cin >> n >> m;
    students.resize(n); // n students who joined cheat
    colors.resize(n);
    fill(colors.begin(),colors.end(),UNWRITTEN);
    for (int i = 0; i < m; i++)
    {
        int first;
        int second;
        cin >> first >> second;
        students[first - 1].push_back(second-1);
        students[second - 1].push_back(first-1);
    }
}
bool dfs(int i, int color){
    colors[i] = color;
    for(int j : students[i]){
        if(colors[j] == colors[i]){
            return false;
        }
        if(!colors[j] && !dfs(j,-color)){
            return false;
        }
    }
    return true;
}
bool isBipart(){
    for(int i = 0; i < n; i++){
        if(!colors[i]&& !dfs(i,1)){
            return false;
        }
    }
    return true;
}
int main()
{
    init_all();
    bool yes = isBipart();
    if(yes){
        cout<<"YES"<<endl;
    }else{
        cout<<"NO"<<endl;
    }
    return 0;
}