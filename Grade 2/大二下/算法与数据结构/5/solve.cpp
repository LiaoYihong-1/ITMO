#include <iostream>
#include <vector>
using namespace std;
vector<int> positions;

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
int min(int a, int b){
    return a<b?a:b;
}

bool check(int mid, int fences, int cows){
    int temp = 1;
    int last = 0;
    for(int i = 1; i < fences; i++){
        if(positions[i] - positions[last] >= mid){
            last = i;
            temp++;
        }
    }
    if(temp >= cows){
        return true;
    }else{
        return false;
    }
}

vector<int> turn(vector<string> v){
    vector<int> result;
    for(string i: v){
        result.emplace_back(stoi(i));
    }
    return result;
}

int main(){
    string input;
    getline(cin,input);
    int fences;
    int cows;
    size_t position = input.find(" ",0);
    string sub1 = input.substr(0,position);
    string sub2 = input.substr(position+1,input.size()-position-1);
    fences = stoi(sub1);
    cows = stoi(sub2);
    getline(cin, input);
    positions = turn(split(input," "));

    int left = 0;
    int right = positions.back() - left;
    int mid;
    while(left <= right){
        mid = (left+right)/2;
        if(check(mid, fences, cows)){
            left = mid + 1;
        }else{
            right = mid - 1;
        }
    }
    cout<<right<<endl;
    return 0;
}