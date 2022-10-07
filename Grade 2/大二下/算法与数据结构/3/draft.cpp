#include <map>
#include <stack>
#include <iostream>
#include <stdbool.h>
using namespace std;
int main(){
    map<string,long> m;
    m["sad"] = 1232;
    bool a = m["s"] == 0;
    m.erase("s");
    auto i = m.find("s");
    bool b = i==m.end();
    cout<<b<<endl;
    cout<<a<<endl;
}