#include <iostream>
#include <stdbool.h>
#include <map>
#include <stack>
using namespace std;
//in order to input a string in right format to stoi()
map<string,stack<int>> createMap(map<string,stack<int>> maps){
    map<string,stack<int>> new_map;
    for(map<string,stack<int>>::iterator i = maps.begin(); i!=maps.end(); i++){
        stack<int> newstk;
        if(!i->second.empty()){
            newstk.emplace(i->second.top());
        }else{
            newstk.emplace(0);
        }
        new_map.insert(pair<string,stack<int>>(i->first,newstk));
    }
    return new_map;
}
inline bool isNumber(const string str)
{
    for(int i = 0 ; i < str.size(); i++){
        if(i==0){
            if(str[i]!='-'&&!isdigit(str[i])){
                return false;
            }
        }else{
            if(!isdigit(str[i])){
                return false;
            }
        }
    }
    return true;
}
void manager(map<string,stack<int>> maps){
    string s;
    while(true){
        getline(cin,s);
        if(s.empty()){
            return;
        }else if(!s.compare("{")){
            manager(createMap(maps));
        }else if(!s.compare("}")){
            return;
        }else{
            size_t position = s.find("=",0);
            string sub1 = s.substr(0,position);
            string sub2 = s.substr(position+1,s.size()-position-1);
            //a = 1
            if(isNumber(sub2)){
                if(!maps[sub1].empty()){
                    maps[sub1].pop();
                }
                maps[sub1].emplace(stoi(sub2));
            }
            //a=b
            else{
                if(!maps.count(sub2)||maps[sub2].empty()){
                    maps[sub2].emplace(0);
                }
                int top_sub2 = maps[sub2].top();
                maps[sub1].emplace(top_sub2);
                cout<<top_sub2<<endl;
            }
        }
    }
}
int main(){
    map<string,stack<int>> maps;
    //main
    manager(maps);
    return 0;
}