#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool sorter(string a, string b){
    for(int i = 0; i < min(a.size(),b.size()); i++){
        if(a[i] != b[i]) {
            return a[i] > b[i];
        }
    }

    if( a.size() == b.size()){
        return false;
    } 
    else if (a.size() < b.size()) {
        return sorter( a , b.substr( a.size() ,b.size() - a.size() ) );
    } else 
    {
        return sorter( a.substr(b.size() , a.size()-b.size()) , b );
    }
}

int main(){
    vector<string> fragements;
    string s = "s";

    while(s!="" && !s.empty() && cin>>s){
        if(s!="" && !s.empty()){
            fragements.emplace_back(s);
        }
    }
    sort(fragements.begin(), fragements.end(), sorter);
    string result = "";
    for (auto & i : fragements) {
        result = result + i;
    }
    cout<<result<<endl;
    return 0;
}
//0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
//a b c d e!f g h!i j k! l! m  n  o  p  q  r! s!  t u!  v  w  x  y  z