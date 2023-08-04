#include <iostream>
#include <vector>

using namespace std;

int main(){
    vector<long> vgp;
    int amount_line;
    string input_num;
    getline(cin,input_num);
    amount_line = stoi(input_num);
    for(int i = 0 ; i < amount_line; i++){
        getline(cin, input_num);
        vgp.emplace_back(stol(input_num));
    }
    long max = 0;
    long sum = 0;
    for(int i = 0; i < vgp.size(); i++){
        sum += vgp[i];
        if(sum < 0){
            sum = 0;
        }else if(sum > max){
            max = sum;
        }
    }
    cout<<max<<endl;
    return 0;
}