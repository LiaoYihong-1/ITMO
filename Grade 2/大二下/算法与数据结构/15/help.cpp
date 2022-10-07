// N. Свинки-копилки
#include <bits/stdc++.h>
using namespace std;
int disjoint_set[101], n, answer = 0;

int check(int x)
{
    return disjoint_set[x] != x ? disjoint_set[x] = check(disjoint_set[x]) : x;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    for (int i = 1; i <= n; ++i)
    {
        disjoint_set[i] = i;
    }
    for (int i = 1; i <= n; i++)
    {
        int x;
        cin >> x;
        int head = check(i);
        int end = check(x);
        if (head != end)
        {
            disjoint_set[end] = head;
            // show();
        }
    }
    for (int i = 1; i <= n; i++)
    {
        if (disjoint_set[i] == i)
        {
            ++answer;
        }
    }
    cout << answer;
    return 0;
}