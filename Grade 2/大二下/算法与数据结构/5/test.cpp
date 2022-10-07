#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> split(string s, string pattern){
    vector<string> result;
    while(s.find(" ")!=string::npos){
        int p = s.find(" ");
        result.emplace_back(s.substr(0, p));
        s = s.substr(p+1, s.size());
    }
    result.emplace_back(s);
    return result;
}

vector<int> turn(vector<string> v){
    vector<int> result;
    for(string i: v){
        result.emplace_back(stoi(i));
    }
    return result;
}
int main(){
    int a = 0;
    cin>>a;
    cout<<a<<endl;
    return 0;
}