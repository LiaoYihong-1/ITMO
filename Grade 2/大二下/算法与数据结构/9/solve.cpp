#include <iostream>
#include <vector>
#include <set>
using namespace std;
int on_ground [100000] = {0};//下标对应序号
set<int> group_on_ground;//position
vector<int> peter_want;
int solve(int n, int k, int p)
{
    int result = 0;
    int on_ground_nums = 0;
    for (int i = 0; i < p; i++)
    {
        //不在地上
        if (on_ground[peter_want[i]] == 0)
        {
            //地上还没满
            if (on_ground_nums < k)
            {
                on_ground_nums++;//放地上
                on_ground[peter_want[i]] = 1;//放地上
                group_on_ground.insert(peter_want[i]);
                //cout<<"放下"<<peter_want[i]<<endl;
            }else{
                //地上放满了，收起一个，放下一个
                
            }
            result++;
        }
    }
    return result;
}

int main()
{
    // n - all cars, p - how many cars peter wants, k - biggest amount of car can be put on ground
    int n, k, p;
    cin >> n >> k >> p;
    for (int i = 0; i < p; i++)
    {
        int j;
        cin >> j;
        peter_want.emplace_back(j);
    }
    int result = solve(n,k,p);
    cout << result<< endl;
    return 0;
}