// P. Авиаперелёты
#include <bits/stdc++.h>
using namespace std;

int full_map[1000][1000], exe_map[1000][1000], visited[1000];
int Max = 2000000000, Min = 0, Mid = 0, n;

void DFS(int p, int flag)
{
    visited[p] = 1;
    for (int i = 0; i < n; ++i)
    {
        if ((flag ? exe_map[i][p] : exe_map[p][i]) and !visited[i])
        {
            DFS(i, flag);
        }
    }
}
bool AllVisited()
{
    for (int i = 0; i < n; i++)
    {
        if (!visited[i])
            return false;
    }
    return true;
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    int tmp = 0;
    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
            cin >> tmp;
            full_map[i][j] = tmp;
            // if (tmp > Max) Max = tmp;
            // if (tmp < Min and tmp != 0) Min = tmp;
        }
    }

    while (Min < Max)
    {
        Mid = (Min + Max) / 2;
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (full_map[i][j] <= Mid)
                {
                    exe_map[i][j] = 1;
                }
                else
                {
                    exe_map[i][j] = 0;
                }
            }
        }

        memset(visited, 0, sizeof(visited));
        DFS(0, 0);
        int flag = 0;

        if (AllVisited())
        {
            memset(visited, 0, sizeof(visited));
            DFS(0, 1);
            if (!AllVisited())
                flag = 1;
        }
        else
            flag = 1;
        if (!flag)
        {
            Max = Mid;
        }
        else
            Min = Mid + 1;
    }

    cout << Min;
}