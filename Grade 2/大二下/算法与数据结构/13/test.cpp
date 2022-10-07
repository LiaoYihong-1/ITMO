#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
struct test
{
    int a;
    struct test* next;
};
vector <struct test> v;

void work(){
    int a = 1000000000;
    int b = 291231;
    struct test s2 = {.a=b,.next = NULL};
    struct test s1 = {.a=a,.next = &s2};
    v.emplace_back(s1);
    v.emplace_back(s2);
    s1.next = &(v.back());
    v[1] = s1;
}

int main()
{
    work();
    struct test t = v.front();
    struct test *w = t.next;
    cout<<(*w).a<<endl; 
    return 0;
}