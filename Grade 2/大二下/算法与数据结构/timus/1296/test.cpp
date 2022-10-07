#include <iostream>
using namespace std;

int main(){
    string a = "abcdefg";
    string b = "de";
    string c = "gggg";
    string::size_type type1 = a.find(b);
    string::size_type type2 = a.find(c);
    int i = type1+1;
    cout<<i<<endl;
    cout<<type1<<endl;
    cout<<type2<<endl;
    return 0;
}