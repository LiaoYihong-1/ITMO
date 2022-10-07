#include <iostream>
#include <stdbool.h>
#include <map>
#include <vector>
using namespace std;
bool isNumber(const string str)
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

map<string,long> move_0(map<string,long> maps){
    vector<string> removed;
    for(auto i = maps.begin(); i != maps.end(); i++){
        if(i->second==0){
            removed.emplace_back(i->first);
        }
    }
    for(string r: removed){
        maps.erase(r);
    }
    return maps;
}

void manager(map<string,long> maps){
    string s;
    while(true){
        getline(cin,s);
        if(s.empty()){
            return;
        }else if(!s.compare("{")){
            maps = move_0(maps);
            manager(maps);
        }else if(!s.compare("}")){
            return;
        }else{
            size_t position = s.find("=",0);
            string sub1 = s.substr(0,position);
            string sub2 = s.substr(position+1,s.size()-position-1);
            //a = 1
            if(isNumber(sub2)){
                maps[sub1] = stol(sub2);
            }
            //a=b
            else{
                maps[sub1] = maps[sub2];
                cout<<maps[sub2]<<endl;
            }
        }
    }
}

int main(){
    map<string,long> maps;
    //main
    manager(maps);
    return 0;
}