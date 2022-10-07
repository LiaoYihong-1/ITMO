#include <iostream>
#include <cstring>
#include <stdbool.h>
#include <algorithm>
#include <stack>
using namespace std;

int main()
{
    string str1;
    cin>>str1;
    stack<pair<int,char>> stk;
    int pointer = 1;
    int pointerForIndex = 0;
    int indexOfAnimals[str1.size()];
    int indexOfIndex[str1.size()/2];
    for(int i = 0; i < str1.size(); i++){
        if(stk.empty()){
            if(islower(str1[i])){
                stk.emplace(pointer,str1[i]);
                pointer++;
            }else{
                stk.emplace(i,str1[i]);
            }
        }else{
            if(str1[i]!=stk.top().second&&(toupper(str1[i])==stk.top().second||tolower(str1[i])==stk.top().second)){
                if(islower(str1[i])){
                    indexOfAnimals[stk.top().first] = pointer;//stk.top().first is the index of trap in str
                    indexOfIndex[pointerForIndex] = stk.top().first;
                    pointerForIndex++;
                    stk.pop();
                    pointer++;
                }else{
                    indexOfAnimals[i] = stk.top().first;
                    indexOfIndex[pointerForIndex] = i;
                    pointerForIndex++;
                    stk.pop();
                }
            }else{
                if(islower(str1[i])){
                    stk.emplace(pointer,str1[i]);
                    pointer++;
                }else{
                    stk.emplace(i,str1[i]);
                }
            }
        }
    }
    if(!stk.empty()){
        cout<<"Impossible";
    }else{
        sort(indexOfIndex,indexOfIndex+str1.size()/2);
        cout<<"Possible"<<endl;
        for(int i = 0; i < str1.size()/2; i++){
            cout<<indexOfAnimals[indexOfIndex[i]]<<" ";
        }
    }
    cout<<endl;
    return 0;
}