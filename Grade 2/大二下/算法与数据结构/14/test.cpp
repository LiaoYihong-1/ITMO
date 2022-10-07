#include <iostream>
#include <deque>
#include <vector>
#include <algorithm>
using namespace std;
deque<int> window;
vector<int> num_list;
int n, k;
vector<int> c;
void init_c(){
    vector<int> n_w(10,1000000);
    c = n_w;
}
int main() {
    vector<int> path(100);
    cout<<path[0]<<endl;
    
}