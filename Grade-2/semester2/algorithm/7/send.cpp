#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
string s;
int amounts[26] = {0};
char my_index[26] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
vector<int> values;
string left_part = "";
string mid_part = "";
string right_part = "";
string result_part = "";

vector<string> split_sgtring(string s_1,const string pattern){
    s_1 = s_1 + pattern;
    size_t position = s_1.find(pattern, 0);
    vector<string> result;
    string sub1;
    while(position != s_1.npos){
        sub1 = s_1.substr(0,position);
        string temp;
        temp = s_1.substr(position+1,s_1.size()-position-1);
        s_1 = temp;
        position = s_1.find(pattern, 0);
        result.emplace_back(sub1);
    }
    return result;
}

void quick_sort_arr(int left, int right){
    if(left >= right) return;
    //normal values sort
    int pivot = values[left];
    int save_started = left;
    int save_end = right;
    //sort index
    char pivot_char = my_index[left];
    //sort amount
    int pivot_amount = amounts[left];
    while(left < right){
        while(left < right && values[right] < pivot){
            right--;
        }
        if(left < right){
            values[left] = values[right];
            my_index[left] = my_index[right];
            amounts[left] = amounts[right];
        }
        while(left < right && values[left] >= pivot){
            left++;
        }
        if(left < right){
            values[right] = values[left];
            my_index[right] = my_index[left];
            amounts[right] = amounts[left];
        }
    }
    values[left] = pivot;
    my_index[left] = pivot_char;
    amounts[left] = pivot_amount;
    quick_sort_arr(save_started, left - 1);
    quick_sort_arr(left+1, save_end);
}

void build_result(){
    for(int i = 0 ; i < 26 ; i++){
        if(amounts[i] > 1){
            right_part = string(1, my_index[i]) + right_part;
            left_part = left_part + string(1, my_index[i]);
            if(amounts[i] > 2){
                mid_part = mid_part + string(amounts[i] - 2, my_index[i]);
            }
        }else if( amounts[i] == 1){
            mid_part = mid_part + string(1, my_index[i]);
        }
    }
    result_part = left_part + mid_part + right_part;
}
/*
void print_arr(){
    cout<<"Amounts:"<<endl;
    for(int i = 0 ;i < 26; i++){
        cout<<amounts[i]<<" ";
    }
    cout<<endl;
    cout<<"Values:"<<endl;
    for(int i = 0 ;i < 26; i++){
        cout<<values[i]<<" ";
    }
    cout<<endl;
    cout<<"Chars"<<endl;
    for(int i = 0 ;i < 26; i++){
        cout<<my_index[i]<<" ";
    }
    cout<<endl;
}*/

int main(){
    cin>>s;//input string
    //input values
    for(int i = 0 ; i < 26;i++){
        int element;
        cin>>element;
        values.emplace_back(element);
    }
    //record amount of each alphabet
    for(int i = 0 ; i < s.size(); i++){
        amounts[s[i] - 97] = amounts[s[i] - 97] + 1;
    }
    quick_sort_arr(0, 25);
    //print_arr();
    //cout<<endl;
    build_result();
    //print_arr();
    //cout<<endl;
    cout<<result_part<<endl;
    return 0;
}