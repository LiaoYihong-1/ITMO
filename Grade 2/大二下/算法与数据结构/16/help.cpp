// O. Долой списывание!
#include <bits/stdc++.h>

using namespace std;

const int WHITE = 0;
const int GRAY = 1;
const int BLACK = 2;
const int base_flag = -1;

int n, m;

vector<vector<int>> ad_matrix;

int status[100] = {WHITE};

bool DFS(int this_p, int last_p)
{
    if (status[this_p] != WHITE)
        return (last_p == base_flag) || (status[last_p] != status[this_p]);
    if (last_p != -1)
    {
        status[this_p] = status[last_p] + 1;
        if (status[this_p] > BLACK)
        {
            status[this_p] = GRAY;
        }
    }
    else
        status[this_p] = GRAY;
    for (auto next : ad_matrix[this_p])
    {
        if (!DFS(next, this_p))
            return false;
    }
    return true;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;

    ad_matrix.resize(n);

    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        ad_matrix[v].push_back(u);
        ad_matrix[u].push_back(v);
    }

    for (int i = 0; i < n; i++)
        if (!DFS(i, base_flag))
        {
            cout << "NO" << endl;
            return 0;
        }

    cout << "YES" << endl;

    return 0;
}