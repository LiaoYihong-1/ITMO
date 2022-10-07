#include <iostream>
#include <stdbool.h>
#include <queue>
#include <stack>
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
//split string with pattern(don't contain last char which will be ';')
vector<string> split_sgtring(const string s,const string pattern){
    size_t position = s.find(pattern,0);
    string sub1 = s.substr(0,position);
    string sub2 = s.substr(position+1,s.size()-position-1);
    vector<string> result;
    result.push_back(sub1);
    result.push_back(sub2);
    return result;
}
//find an empty stack
int find_free(vector<stack<pair<string,long>>> vec){
    if(vec.empty()){
        return -1;
    }
    for(int i = 0; i < vec.size(); i++){
        if(!vec[i].empty()){
            return i;
        }
    }
    return -1;
}
//find the stack in vector, whose string is var
int find_position(vector<stack<pair<string,long>>> vec,string var){
    if(vec.empty()){
        return -1;
    }
    for(int i = 0; i < vec.size(); i++){
        if(!vec[i].empty()&&!vec[i].top().first.compare(var)){
            return i;
        }
    }
    return -1;
}
int main(){
    vector<stack<pair<string,long>>> stks;
    //main
    bool end = false;
    while(true&&!end){
        string s;
        getline(cin,s);
        if(s.empty()){
            end=true;
        }else if(!s.compare("{")){
            if(!stks.empty()){
                for(int i = 0; i < stks.size() ; i++){
                    if(!stks[i].empty()){
                        stks[i].emplace(stks[i].top());
                    }
                }
            }
            continue;
        }else if(!s.compare("}")){
            if(!stks.empty()){
                for(int i = 0; i < stks.size() ; i++){
                    if(!stks[i].empty()){
                        stks[i].pop();
                    }
                }
            }
            continue;
        }else{
            string euqation = "=";
            vector<string> substrings = split_sgtring(s,euqation);
            //a = 1
            if(isNumber(substrings[1])){
                int index_string = find_position(stks,substrings[0]);
                //if position of var not found
                if(index_string == -1){
                    stack<pair<string,long>> newstk;//create a new stack for new variable
                    newstk.emplace(substrings[0],stol(substrings[1]));
                    stks.push_back(newstk);
                    continue;
                }else{
                    if(!stks[index_string].empty()){
                        stks[index_string].pop();
                        stks[index_string].emplace(substrings[0],stol(substrings[1]));
                    }
                    continue;
                }
            }
            //a=b
            else{
                int index_string_var1 = find_position(stks,substrings[0]);
                int index_string_var2 = find_position(stks,substrings[1]);
                //if position of var not found
                if(index_string_var1==-1){
                    stack<pair<string,long>> newstk;//create a new stack for new variable
                    if(index_string_var2 == -1){
                        newstk.emplace(substrings[0],0);
                        stks.push_back(newstk);
                        cout<<0<<endl;
                        continue;
                    }else{
                        newstk.emplace(substrings[0],stks[index_string_var2].top().second);
                        stks.push_back(newstk);
                        cout<<stks[index_string_var2].top().second<<endl;
                        continue;
                    }
                }else{
                    if(index_string_var2 == -1){
                    if(!stks[index_string_var1].empty()){
                        stks[index_string_var1].pop();
                    }stks[index_string_var1].emplace(substrings[0],0);
                        cout<<0<<endl;
                        continue;
                    }else{
                        int read =stks[index_string_var2].top().second;
                        if(!stks[index_string_var1].empty()){
                            stks[index_string_var1].pop();
                        }
                        stks[index_string_var1].emplace(substrings[0],read);
                        cout<<read<<endl;
                        continue;                       
                    }
                }
            }
        }
    }
    return 0;
}