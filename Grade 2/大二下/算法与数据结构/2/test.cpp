#include <iostream>
#include <cstring>
#include <stdbool.h>
using namespace std;
class Trap{
    char type;
    int indexInString;
};
class Animal{
    char type;
    int indexInString;
    int indexInAnimals;
    bool isCaught;
};
int getMax(int a, int b){
    return a>b?a:b;
}
int getMin(int a, int b){
    return a<b?a:b;
}
int main()
{
    char a = 'a';
    int b = a;
    cout<<"a = "<<b<<endl;
    a = 'A';
    b = a;
    cout<<"A = "<<b<<endl;
    return 0;
}