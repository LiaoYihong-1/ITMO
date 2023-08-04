#include <iostream>
#include <deque>
#include <vector>
using namespace std;
int unions[101];
int find_key(int x){
    if(unions[x] == x){
        return x;
    }else{
        return unions[x] = find_key(unions[x]);
    }
}
void union_build(int x,int y){
    int a = find_key(x);
    int b = find_key(y);
    unions[a] = b;
}
int main(){
    int n;
    cin>>n;
    int result = n;
    //index + 1 = uni[index]
    for(int i = 1 ; i <= n ; i++){
        unions[i] = i;
    }
    for(int i = 1 ; i <= n; i++){
        int k;
        cin>>k;
        if(find_key(i)!=find_key(k)){
            union_build(i,k);
            result--;
        }
    }
    cout<<result<<endl;
    return 0;
}