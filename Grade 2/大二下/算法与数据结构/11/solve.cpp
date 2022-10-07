#include <iostream>
#include <deque>
#include <vector>
using namespace std;
deque<int> left_line(0);
deque<int> right_line(0);
vector<int> result_set;
enum corblin{
    NORMAL = 0,
    SPECIAL,
    SAMEN
};

enum corblin judge(string s){
    if(s[0] == '+'){
        return NORMAL;
    }else if(s[0] == '-'){
        return SAMEN;
    }else{
        return SPECIAL;
    }
}



int main(){
    int n;
    cin>>n;
    int k = 0;
    cin.get();
    int output = 0;
    string input;
    for(int i = 0 ; i < n; i++){
        getline(cin, input);
        enum corblin result = judge(input);
        if(left_line.size() < right_line.size() && !right_line.empty()){
            left_line.push_back(right_line.front());
            right_line.pop_front();
        }
        if(result == NORMAL){
            right_line.push_back(stoi(input.substr(2)));
        }else if(result == SPECIAL){
            right_line.push_front(stoi(input.substr(2)));
        }else if(result == SAMEN){
            if(!left_line.empty()){
                result_set.emplace_back(left_line.front());
                left_line.pop_front();
                output++;
            }else if(!right_line.empty()){
                result_set.emplace_back(right_line.front());
                right_line.pop_front();
                output++;
            }
        }
    }
    for(int k = 0 ; k < output; k++){
        cout<<result_set[k]<<endl;
    }
    return 0;
}