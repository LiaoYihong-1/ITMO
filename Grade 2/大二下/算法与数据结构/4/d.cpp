#include <iostream>
using namespace std;

int main()
{
    int a;//start
    int b;//every day will get a*b
    int c;//every day -n
    int d;//max d
    int k;//after k days
    cin >> a >> b >> c >> d >> k;
    //after i+1 day
    for (int i = 0; i < k; i++){
        a=a*b-c;
        if(a<=0){
            a=0;
            break;
        }
        /*
        1) first a<=d
        2) we knew that a*b-c>=0(after testing a*b-c>=0 and a*b>=c doesn't matter)
        3) after the first time all a=d
        */
        else if(a>=d && a*b-c>=0){
            a=d;
            break;
        }
        //this situation a will never change
        else if(a*b-c==a){
            break;
        }
    }
    cout<<a;
}