#include<iostream>  
#include <map>
#include <vector>
#include <set>
#include <stack>
#include <math.h>
using namespace std;
//first int in pair is the num, second is the value  
double my_atan(int y, int x){
    if(y < 0){
        return atan2(y,x) + 2*M_PI;
    }else{
        return atan2(y,x);
    }
}


int main()  
{  
    cout<<my_atan(-1,-1)<<endl<<my_atan(-1,1)<<endl;
    cout<<my_atan(1,1)<<endl<<my_atan(1,-1)<<endl;
    cout<<my_atan(1,0)<<endl;//y
    cout<<my_atan(-1,0)<<endl;//-y
    cout<<my_atan(0,1)<<endl;//x
    cout<<my_atan(0,-1)<<endl;//-x
    return 0;  
}  
