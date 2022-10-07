#include <iostream>
#include <stdbool.h>
#include <map>
#include <stack>
#include <vector>
using namespace std;
//in order to input a string in right format to stoi()
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
//split string with pattern
inline vector<string> split_sgtring(const string s,const string pattern){
    size_t position = s.find(pattern,0);
    string sub1 = s.substr(0,position);
    string sub2 = s.substr(position+1,s.size()-position-1);
    vector<string> result;
    result.push_back(sub1);
    result.push_back(sub2);
    return result;
}
int main(){
    map<string,stack<long>> maps;
    //main
    bool end = false;
    string s;
    while(getline(cin,s)){
        if(!s.compare("{")){
            if(!maps.empty()){
                for(map<string,stack<long>>::iterator i = maps.begin(); i!=maps.end(); i++){
                    if(!i->second.empty()) i->second.emplace(i->second.top());
                }
            }
            continue;
        }else if(!s.compare("}")){
            if(!maps.empty()){
                for(map<string,stack<long>>::iterator i = maps.begin(); i!=maps.end(); i++){
                    if(!i->second.empty()) i->second.pop();
                }
            }
            continue;
        }else{
            size_t position = s.find("=",0);
            string sub1 = s.substr(0,position);
            string sub2 = s.substr(position+1,s.size()-position-1);
            //a = 1
            if(isNumber(sub2)){
                map<string,stack<long>>::iterator index_string = maps.find(sub1);
                //if position of first var not found
                if(index_string == maps.end()){
                    stack<long> newstk;//create a new stack for new variable
                    newstk.emplace(stol(sub2));
                    maps.insert(pair<string,stack<long>>(sub1,newstk));
                    continue;
                }else{
                    //left found and not empty
                    if(!index_string->second.empty()){
                        index_string->second.pop();
                    }//found but empty
                    index_string->second.emplace(stol(sub2));
                    continue;
                }
            }
            //a=b
            else{
                map<string,stack<long>>::iterator index_string_var1 = maps.find(sub1);
                map<string,stack<long>>::iterator index_string_var2 = maps.find(sub2);
                bool b1 = index_string_var1==maps.end();
                bool b2 = index_string_var2==maps.end();
                //if position of var not found
                if(b1){
                    stack<long> newstk;//create a new stack for new variable
                    //var 2 not found or empty
                    if(b2||index_string_var2->second.empty()){
                        newstk.emplace(0);
                        maps.insert(pair<string,stack<long>>(sub1,newstk));
                        cout<<0<<endl;
                        continue;
                    }else{//found and not empty
                        newstk.emplace(index_string_var2->second.top());
                        maps.insert(pair<string,stack<long>>(sub1,newstk));
                        cout<<index_string_var2->second.top()<<endl;
                        continue;
                    }
                }else{//var 1 found
                    if(b2||index_string_var2->second.empty()){//var 2 not found or empty
                        if(!index_string_var1->second.empty()){
                            index_string_var1->second.pop();
                        }
                        index_string_var1->second.emplace(0);
                        cout<<0<<endl;
                        continue;
                    }else{
                        int read =index_string_var2->second.top();
                        if(!index_string_var1->second.empty()){
                            index_string_var1->second.pop();
                        }
                        index_string_var1->second.emplace(read);
                        cout<<read<<endl;
                        continue;                       
                    }
                }
            }
        }
    }
    return 0;
}